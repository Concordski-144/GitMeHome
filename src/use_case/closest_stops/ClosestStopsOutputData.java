package use_case.closest_stops;

import java.util.HashMap;
import java.util.List;

public class ClosestStopsOutputData {
    private HashMap<String, List<Object>> stops;
    private boolean useCaseFailed;

    public ClosestStopsOutputData(HashMap<String, List<Object>> stops, boolean useCaseFailed) {
        this.stops = stops;
        this.useCaseFailed = useCaseFailed;
    }

    public HashMap<String, List<Object>> getStops() {
        return stops;
    }
}
