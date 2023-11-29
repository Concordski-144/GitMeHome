package use_case.next_departures;

import entity.Route;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NextDeparturesInteractor implements NextDeparturesInputBoundary {
    final NextDeparturesDataAccessInterface nextDepartureDataAccessObject;
    final NextDeparturesOutputBoundary nextDeparturePresenter;

    public NextDeparturesInteractor(NextDeparturesDataAccessInterface nextDepartureDataAccessObject, NextDeparturesOutputBoundary nextDeparturesPresenter) {
        this.nextDepartureDataAccessObject = nextDepartureDataAccessObject;
        this.nextDeparturePresenter = nextDeparturesPresenter;
    }


    @Override
    public void execute(NextDeparturesInputData nextDeparturesInputData) {
        List<Route> routes = nextDepartureDataAccessObject.getNextDeparturesByRoute(nextDeparturesInputData.getId(), nextDeparturesInputData.getTime());
        NextDeparturesOutputData nextDepartureOutputData;
        HashMap<String, ArrayList<LocalDateTime>> departureTimes = new HashMap<>();
        for (Route route : routes) {
            ArrayList<LocalDateTime> routeDepartureTimes = route.getDepartureTimes();
            departureTimes.put(route.getid(), routeDepartureTimes);
        }
        nextDepartureOutputData = new NextDeparturesOutputData(departureTimes, false);
        nextDeparturePresenter.prepareSuccessView(nextDepartureOutputData);
    }
}