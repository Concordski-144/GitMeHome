package interface_adapter.NextDepartures;

import use_case.next_departures.NextDeparturesInputBoundary;
import use_case.next_departures.NextDeparturesInputData;

public class NextDeparturesController {

    final NextDeparturesInputBoundary nextDeparturesUseCaseInteractor;

    public NextDeparturesController(NextDeparturesInputBoundary nextDeparturesInteractor) {
        this.nextDeparturesUseCaseInteractor = nextDeparturesInteractor;
    }

    public void execute(String id, int time) {
        NextDeparturesInputData nextDeparturesInputData = new NextDeparturesInputData(id, time);

        nextDeparturesUseCaseInteractor.execute(nextDeparturesInputData);
    }
}
