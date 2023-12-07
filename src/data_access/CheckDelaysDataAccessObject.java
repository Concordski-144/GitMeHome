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
    public static final String API_URL_STATION_NAME = "https://external.transitapp.com/v3/public/search_stops";
    public String lat = "43.6619003";
    public String lon = "-79.3914503";
    public String searchQuery = "";
    public String API_KEY = "";
    public String routeName = "";
    public String stationName = "the Station";

    public String getApiKey() {
        return API_KEY;
    }

    public void setApiKey(String apiKey) {
        this.API_KEY = apiKey;
    }

    public String getRouteName() {
        return routeName;
    }

    public String getStationName() {
        return stationName;
    }


    @Override
    public boolean checkDelaysByRoute(String id) throws RuntimeException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format(API_URL + "?global_route_id=%s&include_next_departure=true", id))
                .addHeader("apiKey", API_KEY)
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            assert response.body() != null;
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                String routeName = responseBody.getJSONObject("route").getString("route_long_name");
                this.routeName = routeName;

                JSONArray allStopsArray = responseBody.getJSONArray("itineraries").getJSONObject(0).getJSONArray("stops");
                ArrayList<Delay> delays = new ArrayList<Delay>();
                DelayFactory delayFactory = new CommonDelayFactory();

                for (int i = 0; i < allStopsArray.length(); i++) {
                    JSONObject stopNextDepartureObject = allStopsArray.getJSONObject(i).getJSONObject("next_departure");
                    if (stopNextDepartureObject.getBoolean("is_cancelled")) {
                        return true;
                    } else if (stopNextDepartureObject.getInt("scheduled_departure_time") != stopNextDepartureObject.getInt("departure_time")) {
                        return true;
                    }
                }
                return false;
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
                .url(String.format(API_URL_BY_STATION + "?global_stop_id=%s", id))
                .addHeader("apiKey", API_KEY)
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            assert response.body() != null;
            JSONObject responseBody = new JSONObject(response.body().string());


            if (response.code() == 200) {
                Request nameRequest = new Request.Builder()
                        .url(String.format(API_URL_STATION_NAME + "?lat=%s&lon=%s&query=\"%s\"", lat, lon, searchQuery))
                        .addHeader("apiKey", API_KEY)
                        .build();
                try{
                    Response nameResponse = client.newCall(nameRequest).execute();
                    System.out.println(nameResponse);
                    assert nameResponse.body() != null;
                    JSONObject nameResponseBody = new JSONObject(nameResponse.body().string());

                    JSONArray allResultsArray = nameResponseBody.getJSONArray("results");
                    for (int i = 0; i < allResultsArray.length(); i++) {
                        JSONObject resultObject = allResultsArray.getJSONObject(i);
                        if (resultObject.getString("global_stop_id").equals(id.substring(0, id.length() - 1)) || resultObject.getString("parent_station_global_stop_id").equals(id.substring(0, id.length() - 1))) {
                            stationName = resultObject.getString("stop_name");
                            break;
                        }
                    }
                } catch (IOException | JSONException e) {
                    stationName = "Station"; // fallback name in case of name not found
                }


                JSONArray allRoutesArray = responseBody.getJSONArray("route_departures");
                ArrayList<Delay> delays = new ArrayList<Delay>();

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
            } else {
                throw new RuntimeException(response.message());
            }

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
