package use_case.next_departures;

import entity.Route;
import java.util.List;
import java.util.ArrayList;

public class NextDeparturesInteractor implements NextDeparturesInputBoundary{
    final NextDeparturesDataAccessInterface nextDeparturesDataAccessObject;
    final NextDeparturesOutputBoundary nextDeparturesPresenter;

    public NextDeparturesInteractor(NextDeparturesDataAccessInterface nextDeparturesDataAccessObject,
                                    NextDeparturesOutputBoundary nextDeparturesPresenter) {
        this.nextDeparturesDataAccessObject = nextDeparturesDataAccessObject;
        this.nextDeparturesPresenter = nextDeparturesPresenter;
    }
    @Override
    public void execute(NextDeparturesInputData nextDeparturesInputData) {
        List<Route> routes = nextDeparturesDataAccessObject.getNextDeparturesByRoute(nextDeparturesInputData.getId());
        NextDeparturesOutputData nextDeparturesOutputData = null;
        for (Route route : routes) {
            if (route.getid().equals(nextDeparturesInputData.getId())){
                ArrayList<Integer> departureTimes = route.getDepartureTimes();
                //are we keeping the hashmap here or will it be an array list?
                nextDeparturesOutputData = new NextDeparturesOutputData(departureTimes, false);
                break;
            }
        }
        nextDeparturesPresenter.prepareSuccessView(nextDeparturesOutputData);
    }
}
