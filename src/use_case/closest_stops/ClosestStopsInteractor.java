package use_case.closest_stops;

public class ClosestStopsInteractor implements ClosestStopsInputBoundary {
    final ClosestStopsDataAccessInterface closestStopsDataAccessInterface;
    final ClosestStopsInputBoundary closestStopsInputBoundary;

    public ClosestStopsInteractor(ClosestStopsDataAccessInterface closestStopsDataAccessInterface,
                                  ClosestStopsInputBoundary closestStopsInputBoundary) {
        this.closestStopsDataAccessInterface = closestStopsDataAccessInterface;
        this.closestStopsInputBoundary = closestStopsInputBoundary;
    }

    @Override
    public void execute(ClosestStopsInputData getClosestStopsInputData) {
        // TODO: 2023-11-26 implement this
    }
}
