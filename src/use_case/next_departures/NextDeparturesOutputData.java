package use_case.next_departures;

import java.util.HashMap;

public class NextDeparturesOutputData {
    private final HashMap<String, Integer[]> departuresByRoute;
    private boolean useCaseFailed;

    public NextDeparturesOutputData(HashMap<String, Integer[]> departuresByRoute, boolean useCaseFailed) {
        this.departuresByRoute = departuresByRoute;
        this.useCaseFailed = useCaseFailed;
    }

    public HashMap<String, Integer[]> getDeparturesByRoute() {
        return departuresByRoute;
    }
}
