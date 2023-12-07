package data_access;

import entity.Route;
import entity.Station;
import entity.SubwayRouteFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.get_details.GetDetailsDataAccessInterface;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class GetDetailsDataAccessObject implements GetDetailsDataAccessInterface {
    public static final String API_URL = "https://external.transitapp.com/v3/public/route_details";
    public static String API_KEY = "1c069f93e535f58bf385122d580d43b4bcbaa3df234cac14ceb62dcb6e7ce7a4";

    public void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }

    public static String getApiKey() {
        return API_KEY;
    }


    public ArrayList<String> getDetails(String id, boolean departure) throws RuntimeException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format("https://external.transitapp.com/v3/public/route_details?global_route_id=%s&include_next_departure=%s", id, departure))
                .addHeader("apiKey", API_KEY)
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            assert response.body() != null;
            JSONObject responseBody = new JSONObject(response.body().string());

            if (response.code() == 200) {
                ArrayList<String> details = new ArrayList<>();
                JSONArray itineraries = responseBody.getJSONArray("itineraries");
                JSONObject route = responseBody.getJSONObject("route");
                String routeName = route.getString("route_short_name") + " "
                        + route.getString("route_long_name");
                boolean isActive = itineraries.getJSONObject(0).getBoolean("is_active");
                int routeType = route.getInt("route_type");
                String routeColor = route.getString("route_color");
                details.add(routeName);
                if (isActive){
                    details.add("Active");
                }
                else{
                    details.add("NotActive");
                }
                details.add(String.valueOf(routeType));
                details.add(routeColor);
                JSONArray stations = itineraries.getJSONObject(0).getJSONArray("stops");

                details.add("stops listed below:");
                if (departure) {
                    for (int i = 0; i < stations.length(); i++) {
                        int time = stations.getJSONObject(i).getJSONObject("next_departure").getInt("departure_time");
                        LocalDateTime localDateTime =
                                LocalDateTime.ofEpochSecond(time,0, OffsetDateTime.now().getOffset());
                        details.add(stations.getJSONObject(i).getString("stop_name") + " " + localDateTime.toString());
                    }
                }
                else{
                    for (int i = 0; i < stations.length(); i++) {
                        details.add(stations.getJSONObject(i).getString("stop_name"));
                    }
                }
                return details;

            } else {
                throw new RuntimeException(response.message());
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }


}
