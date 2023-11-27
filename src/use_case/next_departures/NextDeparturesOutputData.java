package use_case.next_departures;

import java.util.ArrayList;

public class NextDeparturesOutputData {
    private final ArrayList<Integer> departures;
    private boolean useCaseFailed;

    public NextDeparturesOutputData(ArrayList<Integer> departures, boolean useCaseFailed) {
        this.departures = departures;
        this.useCaseFailed = useCaseFailed;
    }

    public ArrayList<Integer> getDeparturesByRoute() {
        return departures;
    }
}
