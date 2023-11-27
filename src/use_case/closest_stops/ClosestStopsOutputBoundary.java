package use_case.closest_stops;

public interface ClosestStopsOutputBoundary {
    void prepareSuccessView(ClosestStopsOutputData response);

    void prepareFailView(String error);
}
