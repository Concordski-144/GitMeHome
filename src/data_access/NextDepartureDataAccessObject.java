package data_access;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.next_departure.NextDepartureDataAccessInterface;

import java.io.IOException;
import java.util.HashMap;

public class NextDepartureDataAccessObject implements NextDepartureDataAccessInterface {
    public static final String API_URL = "https://external.transitapp.com/v3/public/stop_departures";
    public static final String API_TOKEN = "";

    public static String getApiToken() {
        return API_TOKEN;
    }


    @Override
    public HashMap<String, Integer[]> getNextDeparturesByRoute(String id) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format("https://external.transitapp.com/v3/public/stop_departures?global_stop_id=%s", id))
                .addHeader("apiKey", API_TOKEN)
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            assert response.body() != null;
            JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt("status_code") == 200) {
                HashMap<String, Integer[]> departuresByRoute = new HashMap<>();
                JSONObject allRoutesObject = responseBody.getJSONObject("route_departures");
                JSONArray allRoutesArray = allRoutesObject.toJSONArray(allRoutesObject.names());
                for (int i = 0; i < allRoutesArray.length(); i++) {
                    JSONObject routeObject = allRoutesArray.getJSONObject(i);
                    String routeName = routeObject.getString("route_short_name") + " "
                            + routeObject.getString("route_long_name");
                    JSONObject scheduleObject = routeObject.getJSONObject("schedule_items");
                    JSONArray scheduleArray = scheduleObject.toJSONArray(scheduleObject.names());
                }
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }


}
