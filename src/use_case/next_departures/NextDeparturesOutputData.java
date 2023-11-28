package use_case.next_departures;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class NextDeparturesOutputData {
    private final HashMap<String, ArrayList<LocalDateTime>> departuresByRoute;
    private boolean useCaseFailed;

    public NextDeparturesOutputData(HashMap<String,  ArrayList<LocalDateTime>> departuresByRoute, boolean useCaseFailed) {
        this.departuresByRoute = departuresByRoute;
        this.useCaseFailed = useCaseFailed;
    }

    public HashMap<String, ArrayList<LocalDateTime>> getDeparturesByRoute() {
        return departuresByRoute;
    }
}
