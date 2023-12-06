package use_case.get_details;

import use_case.get_details.GetDetailsOutputData;

public interface GetDetailsOutputBoundary {
    void prepareSuccessView(GetDetailsOutputData Details);
    void prepareFailView(String error);
}
