package use_case.next_departures;

import java.util.ArrayList;
import java.util.HashMap;

public class NextDeparturesOutputData {
    private final HashMap<String, ArrayList<Integer>> departuresByRoute;
    private boolean useCaseFailed;

    public NextDeparturesOutputData(HashMap<String,  ArrayList<Integer>> departuresByRoute, boolean useCaseFailed) {
        this.departuresByRoute = departuresByRoute;
        this.useCaseFailed = useCaseFailed;
    }

    public HashMap<String,  ArrayList<Integer>> getDeparturesByRoute() {
        return departuresByRoute;
    }
}
