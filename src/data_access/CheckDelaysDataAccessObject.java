package data_access;

import entity.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.check_delays.CheckDelaysDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;

public class CheckDelaysDataAccessObject implements CheckDelaysDataAccessInterface {
    public static final String API_URL = "https://external.transitapp.com/v3/public/route_details";
    public static final String API_URL_BY_STATION = "https://external.transitapp.com/v3/public/stop_departures";
    public String API_KEY = "";

    public String getApiKey() {
        return API_KEY;
    }

    public void setApiKey(String apiKey) {
        this.API_KEY = apiKey;
    }


    @Override
    public ArrayList<Delay> checkDelaysByRoute(String id) throws RuntimeException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format("https://external.transitapp.com/v3/public/route_details?global_route_id=%s&include_next_departure=true", id))
                .addHeader("apiKey", API_KEY)
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            assert response.body() != null;
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                JSONArray allItinerariesArray = responseBody.getJSONArray("itineraries");
                ArrayList<Delay> delays = new ArrayList<Delay>();
                DelayFactory delayFactory = new CommonDelayFactory();

                for (int i = 0; i < allItinerariesArray.length(); i++) {
                    JSONObject routeObject = allItinerariesArray.getJSONObject(i);
                    String routeName = routeObject.getString("route_short_name") + " "
                            + routeObject.getString("route_long_name");

                    JSONArray scheduleArray = routeObject.getJSONArray("itineraries")
                            .getJSONObject(0).getJSONArray("schedule_items");

                    ArrayList<Integer> departures = new ArrayList<Integer>();

                    for (int j = 0; j < scheduleArray.length(); j++) {
                        departures.add(scheduleArray.getJSONObject(i).getInt("departure_time"));
                    }
                    Station[] stations = {};
                    Delay delay = delayFactory.create("Common Delay", id, "Common Delay", "Delayed", 0, 0, new ArrayList<Route>());
                    delays.add(delay);
                }
                return delays;
            } else {
                throw new RuntimeException(response.message());
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean checkDelaysByStation(String id) throws RuntimeException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format(API_URL + "?global_stop_id=%s", id))
                .addHeader("apiKey", API_KEY)
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            assert response.body() != null;
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                JSONArray allRoutesArray = responseBody.getJSONArray("route_departures");
                ArrayList<Route> routes = new ArrayList<Route>();

                for (int i = 0; i < allRoutesArray.length(); i++){
                    JSONObject routeObject = allRoutesArray.getJSONObject(i);

                    JSONArray scheduleArray = routeObject.getJSONArray("itineraries")
                            .getJSONObject(0).getJSONArray("schedule_items");

                    for (int j = 0; j < scheduleArray.length(); j++) {
                        if (scheduleArray.getJSONObject(j).getBoolean("is_cancelled")) {
                            return true;
                        } else if (scheduleArray.getJSONObject(j).getInt("scheduled_departure_time") != scheduleArray.getJSONObject(j).getInt("departure_time")) {
                            return true;
                        }
                    }
                }
            }

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
