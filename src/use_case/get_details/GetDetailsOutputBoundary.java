package use_case.get_details;

public interface GetDetailsOutputBoundary {
    void prepareSuccessView(GetDetailsOutputData Details);
    void prepareFailView(GetDetailsOutputData error);
}
