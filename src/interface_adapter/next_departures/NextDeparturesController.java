package interface_adapter.next_departures;

import use_case.next_departures.NextDeparturesInputBoundary;
import use_case.next_departures.NextDeparturesInputData;

import java.time.LocalDateTime;

public class NextDeparturesController {

    final NextDeparturesInputBoundary nextDeparturesUseCaseInteractor;

    public NextDeparturesController(NextDeparturesInputBoundary nextDeparturesInteractor) {
        this.nextDeparturesUseCaseInteractor = nextDeparturesInteractor;
    }

    public void execute(String id, LocalDateTime time) {
        NextDeparturesInputData nextDeparturesInputData = new NextDeparturesInputData(id, time);

        nextDeparturesUseCaseInteractor.execute(nextDeparturesInputData);
    }
}
