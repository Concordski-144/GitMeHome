package use_case.get_details;

import use_case.next_departures.NextDepartureOutputData;

public interface GetDetailsOutputBoundary {
    void prepareSuccessView(GetDetailsOutputData Details);
    void prepareFailView(GetDetailsOutputData error);
}
