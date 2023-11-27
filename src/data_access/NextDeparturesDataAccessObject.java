package data_access;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.next_departures.NextDeparturesDataAccessInterface;
import entity.Route;
import entity.SubwayRouteFactory;
import entity.Station;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NextDeparturesDataAccessObject implements NextDeparturesDataAccessInterface {
    public static final String API_URL = "https://external.transitapp.com/v3/public/stop_departures";
    public static String API_KEY = "";

    public void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }

    public static String getApiKey() {
        return API_KEY;
    }


    @Override
    public List<Route> getNextDeparturesByRoute(String id) throws RuntimeException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format("https://external.transitapp.com/v3/public/stop_departures?global_stop_id=%s", id))
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
                SubwayRouteFactory subwayRouteFactory = new SubwayRouteFactory();
                for (int i = 0; i < allRoutesArray.length(); i++) {
                    JSONObject routeObject = allRoutesArray.getJSONObject(i);
                    String routeName = routeObject.getString("route_short_name") + " "
                            + routeObject.getString("route_long_name");
                    JSONArray scheduleArray = routeObject.getJSONArray("itineraries")
                            .getJSONObject(0).getJSONArray("schedule_items");
                    ArrayList<Integer> departures = new ArrayList<Integer>();
                    for (int j = 0; j < scheduleArray.length(); j++) {
                        departures.add(scheduleArray.getJSONObject(i).getInt("departure_time"));
                    }
                    Station[] stations = {};
                    Route route = subwayRouteFactory.create(routeName, id, stations);
                    route.setDepartureTimes(departures);
                    routes.add(route);
                }
                return routes;
            } else {
                throw new RuntimeException(response.message());
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
