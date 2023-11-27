package interface_adapter.closest_stops;

import use_case.closest_stops.ClosestStopsInputBoundary;
import use_case.closest_stops.ClosestStopsInputData;

public class ClosestStopsController {

    final ClosestStopsInputBoundary closestStopsInteractor;
    public ClosestStopsController(ClosestStopsInputBoundary closestStopsInteractor) {
        this.closestStopsInteractor = closestStopsInteractor;
    }

    public void execute(double longitude, double latitude, int numberOfStopsDesired) {
        ClosestStopsInputData closestStopsInputData = new ClosestStopsInputData(longitude, latitude, numberOfStopsDesired);

        closestStopsInteractor.execute(closestStopsInputData);
    }
}
