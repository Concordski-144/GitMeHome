package use_case.next_departures;

import entity.Route;

import java.util.ArrayList;
import java.util.HashMap;
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
        List<Route> routes = nextDepartureDataAccessObject.getNextDeparturesByRoute(nextDeparturesInputData.getId(), nextDeparturesInputData.getTime());
        NextDepartureOutputData nextDepartureOutputData = null;
        HashMap<String, ArrayList<Integer>> departureTimes = null;
        for (Route route : routes) {
            ArrayList<Integer> routeDepartureTimes = route.getDepartureTimes();
            departureTimes.put(route.getid(), routeDepartureTimes);
        }
        nextDepartureOutputData = new NextDepartureOutputData(departureTimes, false);
        nextDeparturePresenter.prepareSuccessView(nextDepartureOutputData);
    }
}
