package use_case.next_departures;

public interface NextDeparturesOutputBoundary {
    void prepareSuccessView(NextDeparturesOutputData Route);
    void prepareFailView(String error);
}
