package use_case.next_departures;

import entity.Route;

import java.util.ArrayList;
import java.util.HashMap;
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
        List<Route> routes = nextDepartureDataAccessObject.getNextDeparturesByRoute(nextDeparturesInputData.getId(), nextDeparturesInputData.getTime());
        NextDepartureOutputData nextDepartureOutputData;
        HashMap<String, ArrayList<Integer>> departureTimes = null;
        for (Route route : routes) {
            ArrayList<Integer> routeDepartureTimes = route.getDepartureTimes();
            departureTimes.put(route.getid(), routeDepartureTimes);
        }
        nextDepartureOutputData = new NextDepartureOutputData(departureTimes, false);
        nextDeparturesPresenter.prepareSuccessView(nextDepartureOutputData);
    }
}
