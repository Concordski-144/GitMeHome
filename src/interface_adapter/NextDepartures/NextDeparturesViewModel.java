package interface_adapter.NextDepartures;

import use_case.next_departures.NextDepartureOutputData;

public class NextDeparturesViewModel {

    public static void getNextDepartures(NextDepartureOutputData station) {
    }

    public static void getDeparturesError(NextDepartureOutputData error) {
    }

    public static NextDeparturesState getState() {
        return nextDepartures;
    }
}
