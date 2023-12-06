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
    public static String API_KEY = "";

    public void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }

    public static String getApiKey() {
        return API_KEY;
    }


    public ArrayList<String> getDetails(String id, boolean departure) throws RuntimeException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format("https://external.transitapp.com/v3/public/route_details?global_stop_id=%s&time=%s", id, departure))
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
                boolean isActive = itineraries.getBoolean(8);
                int routeType = route.getInt("route_type");
                String routeColor = route.getString("route_color");
                details.add(routeName);
                details.add(Boolean.toString(isActive));
                details.add(String.valueOf(routeType));
                details.add(routeColor);
                JSONArray stations = itineraries.getJSONObject(0).getJSONArray("stops");
                int departure_time = itineraries.getJSONObject(0).getInt("departure_time");
                for (int i = 0; i < stations.length(); i++) {
                    details.add(stations.getJSONObject(i).getString("stop_name"));
                }
                if (departure){
                    LocalDateTime localDateTime =
                            LocalDateTime.ofEpochSecond(departure_time,
                                    0, OffsetDateTime.now().getOffset());
                    details.add(localDateTime.toString());
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
