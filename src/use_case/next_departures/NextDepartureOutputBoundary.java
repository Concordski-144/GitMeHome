package use_case.next_departures;

public interface NextDepartureOutputBoundary {
    void prepareSuccessView(NextDepartureOutputData user);

    void prepareFailView(String error);
}