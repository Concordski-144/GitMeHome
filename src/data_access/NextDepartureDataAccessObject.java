package data_access;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.next_departures.NextDepartureDataAccessInterface;
import entity.Route;
import entity.SubwayRoute;
import entity.SubwayRouteFactory;
import entity.Station;
import use_case.plan_a_trip.PlanATripDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NextDepartureDataAccessObject implements NextDepartureDataAccessInterface, PlanATripDataAccessInterface {
    public static final String API_URL = "https://external.transitapp.com/v3/public/stop_departures";
    public static final String API_TOKEN = "e418c1e8920c5d9af536656ada565039ba75d7bf015079628a8dc32db1cc9fc9";

    public static String getApiToken() {
        return API_TOKEN;
    }


    @Override
    public List<Route> getNextDeparturesByRoute(String id, int time) throws RuntimeException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format("https://external.transitapp.com/v3/public/stop_departures?global_stop_id=%s&time=%s", id, time))
                .addHeader("apiKey", API_TOKEN)
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            assert response.body() != null;
            JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt("status_code") == 200) {
                JSONObject allRoutesObject = responseBody.getJSONObject("route_departures");
                JSONArray allRoutesArray = allRoutesObject.toJSONArray(allRoutesObject.names());
                ArrayList<Route> routes = new ArrayList<Route>();
                SubwayRouteFactory subwayRouteFactory = new SubwayRouteFactory();
                for (int i = 0; i < allRoutesArray.length(); i++) {
                    JSONObject routeObject = allRoutesArray.getJSONObject(i);
                    String routeName = routeObject.getString("route_short_name") + " "
                            + routeObject.getString("route_long_name");
                    JSONObject scheduleObject = routeObject.getJSONObject("schedule_items");
                    JSONArray scheduleArray = scheduleObject.toJSONArray(scheduleObject.names());
                    ArrayList<Integer> departures = new ArrayList<Integer>();
                    for (int j = 0; j < scheduleArray.length(); j++) {
                        departures.add(scheduleArray.getJSONObject(i).getInt("departure_time"));
                    }
                    Station[] stations = {};
                    SubwayRoute route = (SubwayRoute) subwayRouteFactory.create(routeName, id, stations);
                    route.setDepartureTimes(departures);
                    routes.add(route);                }
                return routes;
            } else {
                throw new RuntimeException(responseBody.getString("error"));
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
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
