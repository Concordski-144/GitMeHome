package use_case.next_departures;

import java.util.ArrayList;
import java.util.HashMap;

public class NextDepartureOutputData {
    private final ArrayList<Integer> departures;
    private boolean useCaseFailed;

    public NextDepartureOutputData(ArrayList<Integer> departures, boolean useCaseFailed) {
        this.departures = departures;
        this.useCaseFailed = useCaseFailed;
    }

    public ArrayList<Integer> getDeparturesByRoute() {
        return departures;
    }
}
