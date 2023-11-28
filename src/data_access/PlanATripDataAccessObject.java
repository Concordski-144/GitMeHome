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

                double fromLat = planObject.getJSONObject("from").getDouble("lat");
                planMap.put("fromLat", fromLat);
                double fromLon = planObject.getJSONObject("from").getDouble("lon");
                planMap.put("fromLon", fromLon);
                String fromName = planObject.getJSONObject("from").getString("name");
                planMap.put("fromName", fromName);

                double toLat = planObject.getJSONObject("to").getDouble("lat");
                planMap.put("toLat", toLat);
                double toLon = planObject.getJSONObject("to").getDouble("lon");
                planMap.put("toLon", toLon);
                String toName = planObject.getJSONObject("to").getString("name");
                planMap.put("toName", toName);

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

                        double legsFromLat = legs.getJSONObject(i).getJSONObject("from").getDouble("lat");
                        temp.put("legsFromLat", legsFromLat);
                        double legsFromLon = legs.getJSONObject(i).getJSONObject("from").getDouble("lon");
                        temp.put("legsFromLon", legsFromLon);
                        String legsFromName = legs.getJSONObject(i).getJSONObject("from").getString("name");
                        temp.put("legsFromName", legsFromName);

                        double legsToLat = legs.getJSONObject(i).getJSONObject("to").getDouble("lat");
                        temp.put("legsToLat", legsToLat);
                        double legsToLon = legs.getJSONObject(i).getJSONObject("to").getDouble("lon");
                        temp.put("legsToLon", legsToLon);
                        String legsToName = legs.getJSONObject(i).getJSONObject("to").getString("name");
                        temp.put("legsToName", legsToName);

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
}
