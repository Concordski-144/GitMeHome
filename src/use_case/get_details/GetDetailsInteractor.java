package use_case.get_details;

import use_case.get_details.GetDetailsDataAccessInterface;
import use_case.get_details.GetDetailsOutputBoundary;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class GetDetailsInteractor implements GetDetailsInputBoundary{
    final GetDetailsDataAccessInterface getDetailsDataAccessObject;
    final GetDetailsOutputBoundary getDetailsPresenter;

    public GetDetailsInteractor(GetDetailsDataAccessInterface getDetailsDataAccessObject, GetDetailsOutputBoundary getDetailsPresenter) {
        this.getDetailsDataAccessObject = getDetailsDataAccessObject;
        this.getDetailsPresenter = getDetailsPresenter;
    }
    @Override
    public void execute(GetDetailsInputData getDetailsInputData) {
        ArrayList<String> details = getDetailsDataAccessObject.getDetails(getDetailsInputData.getRouteID(), getDetailsInputData.isWithDepartureTime());
    }
}
