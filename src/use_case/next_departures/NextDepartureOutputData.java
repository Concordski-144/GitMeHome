package use_case.next_departures;

import java.util.ArrayList;
import java.util.HashMap;

public class NextDepartureOutputData {
    private final HashMap<String, ArrayList<Integer>> departures;
    private boolean useCaseFailed;

    public NextDepartureOutputData(HashMap<String, ArrayList<Integer>> departures, boolean useCaseFailed) {
        this.departures = departures;
        this.useCaseFailed = useCaseFailed;
    }

    public HashMap<String, ArrayList<Integer>> getDeparturesByRoute() {
        return departures;
    }
}
