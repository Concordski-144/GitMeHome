package data_access;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.plan_a_trip.PlanATripDataAccessInterface;

import java.io.IOException;
import java.util.HashMap;

public class PlanATripDataAccessObject implements PlanATripDataAccessInterface {
    public static final String API_URL = "https://external.transitapp.com/v3/otp/plan";
    public static final String API_TOKEN = "";

    public static String getApiToken() {
        return API_TOKEN;
    }

    @Override
    public HashMap<String, Object> getPlanForTrip(String fromPlace, String toPlace) throws RuntimeException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format("https://external.transitapp.com/v3/otp/plan?fromPlace=%s&toPlace=%s", fromPlace, toPlace))
                .addHeader("apiKey", API_TOKEN)
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            assert response.body() != null;
            JSONObject responseBody = new JSONObject(response.body().string());
            System.out.println(responseBody.getInt("status_code"));

            if (responseBody.getInt("status_code") == 200) {
                JSONObject planObject = responseBody.getJSONObject("plan");
                int date = planObject.getInt("date");
                JSONObject fromObject = planObject.getJSONObject("from");
                HashMap<String, Object> fromMap = createPlaceHashMap(fromObject);
                JSONObject toObject = planObject.getJSONObject("to");
                HashMap<String, Object> toMap = createPlaceHashMap(toObject);
                JSONArray itinerariesArray = planObject.getJSONArray("itineraries");
                HashMap<String, Object> planMap = new HashMap<>();
                planMap.put("date", date);
                planMap.put("from", fromMap);
                planMap.put("to", toMap);
                planMap.put("itineraries", itinerariesArray);
                return planMap;
            } else {
                throw new RuntimeException(responseBody.getString("error"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private HashMap<String, Object> createPlaceHashMap(JSONObject fromObject) {
        HashMap<String, Object> place = new HashMap<>();
        place.put("lat", fromObject.getDouble("lat"));
        place.put("lon", fromObject.getDouble("lon"));
        place.put("name", fromObject.getString("name"));
        place.put("type", fromObject.getString("vertexType"));
        return place;
    }
}
