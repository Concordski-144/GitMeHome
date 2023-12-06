package use_case.check_delays;

import use_case.check_delays.CheckDelaysOutputData;

public interface CheckDelaysOutputBoundary {
    void prepareSuccessView(CheckDelaysOutputData delays);
    void prepareFailView(String error);
}
