package interface_adapter.next_departures;

import use_case.next_departures.NextDeparturesInputBoundary;
import use_case.next_departures.NextDeparturesInputData;

public class NextDeparturesController {

    final NextDeparturesInputBoundary nextDeparturesUseCaseInteractor;

    public NextDeparturesController(NextDeparturesInputBoundary nextDeparturesInteractor) {
        this.nextDeparturesUseCaseInteractor = nextDeparturesInteractor;
    }

    public void execute(String id, int time) {
        NextDeparturesInputData signupInputData = new NextDeparturesInputData(id, time);

        nextDeparturesUseCaseInteractor.execute(signupInputData);
    }
}
