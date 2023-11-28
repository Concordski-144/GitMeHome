package interface_adapter.next_departures;

import use_case.next_departures.NextDeparturesInputBoundary;
import use_case.next_departures.NextDeparturesInputData;
import use_case.next_departures.NextDeparturesOutputData;

import java.time.LocalDateTime;

public class NextDeparturesController {
    final NextDeparturesInputBoundary nextDeparturesInteractor;

    public NextDeparturesController (NextDeparturesInputBoundary nextDeparturesInteractor) {
        this.nextDeparturesInteractor = nextDeparturesInteractor;
    }

    public void execute(String stationID, LocalDateTime time) {
        NextDeparturesInputData nextDeparturesInputData = new NextDeparturesInputData(stationID, time);
        nextDeparturesInteractor.execute(nextDeparturesInputData);
    }
}
