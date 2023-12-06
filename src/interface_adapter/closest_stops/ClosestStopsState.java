package interface_adapter.closest_stops;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClosestStopsState {
    private HashMap<String, List<Object>> closestStops;
    private String lon;
    private String lat;
    private String closestStopsError = null;

    public ClosestStopsState(ClosestStopsState copy) {
        closestStops = copy.closestStops;
        lon = copy.lon;
        lat = copy.lat;
        closestStopsError = copy.closestStopsError;
    }

    // explicit default constructor
    public ClosestStopsState() {}

    public HashMap<String, List<Object>> getClosestStops() {
        return closestStops;
    }

    public void setClosestStops(HashMap<String, List<Object>> closestStops) {
        this.closestStops = closestStops;
    }

    public String getClosestStopsError() {
        return closestStopsError;
    }

    public void setClosestStopsError(String closestStopsError) {
        this.closestStopsError = closestStopsError;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
