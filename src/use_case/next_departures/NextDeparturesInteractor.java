package use_case.next_departures;

import entity.Route;

import java.util.ArrayList;
import java.util.List;

public class NextDeparturesInteractor implements NextDeparturesInputBoundary{
    final NextDepartureDataAccessInterface nextDepartureDataAccessObject;
    final NextDeparturesInputBoundary nextDeparturesPresenter;

    public NextDeparturesInteractor(NextDepartureDataAccessInterface nextDepartureDataAccessObject, NextDeparturesInputBoundary nextDeparturesPresenter) {
        this.nextDepartureDataAccessObject = nextDepartureDataAccessObject;
        this.nextDeparturesPresenter = nextDeparturesPresenter;
    }


    @Override
    public void execute(NextDeparturesInputData nextDeparturesInputData) {
        List<Route> routes = nextDepartureDataAccessObject.getNextDeparturesByRoute(nextDeparturesInputData.getId());
        NextDepartureOutputData nextDepartureOutputData = null;
        for (Route route : routes) {
            if (route.getid().equals(nextDeparturesInputData.getId())) {
                ArrayList<Integer> departureTimes = route.getDepartureTimes();
                nextDepartureOutputData = new NextDepartureOutputData(departureTimes, false);
                break;
            }
        }
        nextDeparturesPresenter.prepareSuccessView(nextDepartureOutputData);
    }
}
