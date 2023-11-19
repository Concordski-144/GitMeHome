package use_case.next_departures;

import entity.Route;

import java.util.ArrayList;
import java.util.List;

public class NextDepartureInteractor implements NextDepartureInputBoundary {
    final NextDepartureDataAccessInterface nextDepartureDataAccessObject;
    final NextDepartureOutputBoundary nextDeparturePresenter;

    public NextDepartureInteractor(NextDepartureDataAccessInterface nextDepartureDataAccessObject, NextDepartureOutputBoundary nextDeparturesPresenter) {
        this.nextDepartureDataAccessObject = nextDepartureDataAccessObject;
        this.nextDeparturePresenter = nextDeparturesPresenter;
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
        nextDeparturePresenter.prepareSuccessView(nextDepartureOutputData);
    }
}
