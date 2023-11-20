package use_case.next_departures;

public interface NextDeparturesOutputBoundary {
    void prepareSuccessView(NextDepartureOutputData Route);
    void prepareFailView(NextDepartureOutputData error);
}
