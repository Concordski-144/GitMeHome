package interface_adapter.closest_stops;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClosestStopsState {
    private HashMap<String, List<Object>> closestStops;
    private String closestStopsError;

    public ClosestStopsState(ClosestStopsState copy) {
        closestStops = copy.closestStops;
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

    @Override
    public String toString() {
        String formatted = "";
        for (Map.Entry<String, List<Object>> entry : closestStops.entrySet()) {
            String id = entry.getKey();
            String value = entry.getValue().toString();
            formatted = formatted + "id=" + id + "attributes=" + value + "\n";
        }
        return "Closest stops state {\n" + formatted + "}";
    }
}
