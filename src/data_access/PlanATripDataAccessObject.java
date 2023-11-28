package data_access;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.plan_a_trip.PlanATripDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class PlanATripDataAccessObject implements PlanATripDataAccessInterface {
    public static final String API_URL = "https://external.transitapp.com/v3/otp/plan";
    public static final String API_TOKEN = "e418c1e8920c5d9af536656ada565039ba75d7bf015079628a8dc32db1cc9fc9";

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

            if (response.code() == 200) {
                JSONObject planObject = responseBody.getJSONObject("plan");
                HashMap<String, Object> planMap = new HashMap<>();

                JSONObject fromObject = planObject.getJSONObject("from");
                HashMap<String, Object> fromMap = createPlaceHashMap(fromObject);
                planMap.put("from", fromMap);

                JSONObject toObject = planObject.getJSONObject("to");
                HashMap<String, Object> toMap = createPlaceHashMap(toObject);
                planMap.put("to", toMap);

                JSONArray itinerariesArray = planObject.getJSONArray("itineraries");
                if (!itinerariesArray.isNull(0)) {
                    JSONObject firstItinerary = itinerariesArray.getJSONObject(0);
                    planMap.put("duration", firstItinerary.getInt("duration"));
                    planMap.put("transitTime", firstItinerary.getInt("transitTime"));
                    planMap.put("walkTime", firstItinerary.getInt("walkTime"));
                    JSONArray legs = firstItinerary.getJSONArray("legs");
                    ArrayList<HashMap<String, Object>> legsNeeded = new ArrayList<>();
                    for (int i = 0; i < legs.length(); i++) {
                        HashMap<String, Object> temp = new HashMap<>();
                        temp.put("duration", legs.getJSONObject(i).getInt("duration"));
                        temp.put("mode", legs.getJSONObject(i).getString("mode"));
                        temp.put("from", createPlaceHashMap(legs.getJSONObject(i).getJSONObject("from")));
                        temp.put("to", createPlaceHashMap(legs.getJSONObject(i).getJSONObject("to")));
                        legsNeeded.add(temp);
                    }
                    planMap.put("legs", legsNeeded);
                }
                else {
                    planMap.put("legs", null);
                }

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
        return place;
    }
}
