package use_case.next_departures;

import entity.Route;

import java.util.ArrayList;
import java.util.List;

public class NextDeparturesInteractor implements NextDeparturesInputBoundary{
    final NextDeparturesDataAccessInterface nextDepartureDataAccessObject;
    final NextDeparturesInputBoundary nextDeparturesPresenter;

    public NextDeparturesInteractor(NextDeparturesDataAccessInterface nextDepartureDataAccessObject, NextDeparturesInputBoundary nextDeparturesPresenter) {
        this.nextDepartureDataAccessObject = nextDepartureDataAccessObject;
        this.nextDeparturesPresenter = nextDeparturesPresenter;
    }


    @Override
    public void execute(NextDeparturesInputData nextDeparturesInputData) {
        List<Route> routes = nextDepartureDataAccessObject.getNextDeparturesByRoute(nextDeparturesInputData.getId());
        NextDeparturesOutputData nextDeparturesOutputData = null;
        for (Route route : routes) {
            if (route.getid().equals(nextDeparturesInputData.getId())) {
                ArrayList<Integer> departureTimes = route.getDepartureTimes();
                nextDeparturesOutputData = new NextDeparturesOutputData(departureTimes, false);
                break;
            }
        }
        // nextDeparturesPresenter.prepareSuccessView(nextDepartureOutputData);
    }
}
