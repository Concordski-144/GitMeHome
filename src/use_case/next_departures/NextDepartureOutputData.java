package use_case.next_departures;

import java.util.HashMap;

public class NextDepartureOutputData {
    private final HashMap<String, Integer[]> departuresByRoute;
    private boolean useCaseFailed;

    public NextDepartureOutputData(HashMap<String, Integer[]> departuresByRoute, boolean useCaseFailed) {
        this.departuresByRoute = departuresByRoute;
        this.useCaseFailed = useCaseFailed;
    }

    public HashMap<String, Integer[]> getDeparturesByRoute() {
        return departuresByRoute;
    }
}
