package interface_adapter.next_departures;

import use_case.next_departures.NextDepartureInputBoundary;
import use_case.next_departures.NextDeparturesInputData;

public class NextDepartureController {

    final NextDepartureInputBoundary nextDepartureInteractor;
    public NextDepartureController(NextDepartureInputBoundary nextDepartureInteractor) {
        this.nextDepartureInteractor = nextDepartureInteractor;
    }

    public void execute(String stationID, Integer time) {
        NextDeparturesInputData nextDepartureInputData = new NextDeparturesInputData(
                stationID, time);

        nextDepartureInteractor.execute(nextDepartureInputData);
    }
}
