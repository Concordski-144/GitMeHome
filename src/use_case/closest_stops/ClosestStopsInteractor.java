package use_case.closest_stops;

import entity.Station;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClosestStopsInteractor implements ClosestStopsInputBoundary {
    final ClosestStopsDataAccessInterface closestStopsDataAccessObject;
    final ClosestStopsOutputBoundary closestStopsPresenter;

    public ClosestStopsInteractor(ClosestStopsDataAccessInterface closestStopsDataAccessObject,
                                  ClosestStopsOutputBoundary closestStopsPresenter) {
        this.closestStopsDataAccessObject = closestStopsDataAccessObject;
        this.closestStopsPresenter = closestStopsPresenter;
    }

    @Override
    public void execute(ClosestStopsInputData closestStopsInputData) {
        // TODO: 2023-11-26 implement this
        double lon = closestStopsInputData.getCoordinates()[0];
        double lat = closestStopsInputData.getCoordinates()[1];
        int num = closestStopsInputData.getNumberOfStopsDesired();
        List<Station> stops = closestStopsDataAccessObject.getClosestStops(lon, lat, num);
        if (stops.isEmpty()) {
            closestStopsPresenter.prepareFailView("No stops found near user");
        } else {
            HashMap<String, List<Object>> output = new HashMap<>();
            for (Station station : stops) {
                String key = station.getid();
                ArrayList<Object> value = new ArrayList<>();
                value.add(station.getName());
                value.add(station.getDistanceFromUser());
                value.add(station.getAccessibility());
                output.put(key, value);
            }
            ClosestStopsOutputData closestStopsOutputData = new ClosestStopsOutputData(output, false);
            closestStopsPresenter.prepareSuccessView(closestStopsOutputData);
        }
    }
}
