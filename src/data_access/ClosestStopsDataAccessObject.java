package data_access;

import entity.CommonStationFactory;
import entity.Station;
import entity.StationFactory;
import entity.Route;
import use_case.closest_stops.ClosestStopsDataAccessInterface;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ClosestStopsDataAccessObject implements ClosestStopsDataAccessInterface {
    public static final String API_URL = "https://external.transitapp.com/v3/public/nearby_stops";
    public String API_KEY = "";

    public String getApiKey() {
        return API_KEY;
    }

    public void setApiKey(String apiKey) {
        this.API_KEY = apiKey;
    }

    @Override
    public List<Station> getClosestStops(double lon, double lat, int num) throws RuntimeException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format(API_URL + "?lat=%s&lon=%s", lon, lat))
                .addHeader("apiKey", API_KEY)
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            assert response.body() != null;
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                JSONArray allStops = responseBody.getJSONArray("stops");
                // need to sort allStops by distance from user
                ArrayList<JSONObject> allStopsList = new ArrayList<>();
                for (int i = 0; i < allStops.length(); i++) {
                    allStopsList.add(allStops.getJSONObject(i));
                }
                allStopsList.sort(new Comparator<>() {
                    public static final String KEY_NAME = "distance";
                    @Override
                    public int compare(JSONObject o1, JSONObject o2) {
                        Integer dist1 = o1.getInt(KEY_NAME);
                        Integer dist2 = o2.getInt(KEY_NAME);
                        return dist1.compareTo(dist2);
                    }
                });
                StationFactory stationFactory = new CommonStationFactory();
                ArrayList<Station> closestStops = new ArrayList<>();
                for (int j = 0; j < Math.min(num, allStopsList.size()); j++) {
                    JSONObject stop = allStopsList.get(j);
                    String name = stop.getString("stop_name");
                    String id = stop.getString("global_stop_id");
                    double longitude = stop.getDouble("stop_lon");
                    double latitude = stop.getDouble("stop_lat");
                    boolean accessibility = (stop.getInt("wheelchair_boarding") != 0);
                    int transitMode = stop.getInt("route_type");
                    int distance_from_user = stop.getInt("distance");
                    Route[] routes = {};
                    closestStops.add(stationFactory.create(name, id, longitude, latitude, accessibility, transitMode, distance_from_user, routes));
                }
                return closestStops;
            } else {
                throw new RuntimeException(response.message());
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
