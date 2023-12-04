package interface_adapter.check_delays;

import interface_adapter.ViewManagerModel;
import interface_adapter.check_delays.CheckDelaysState;
import interface_adapter.check_delays.CheckDelaysViewModel;
import use_case.check_delays.CheckDelaysOutputBoundary;
import use_case.check_delays.CheckDelaysOutputData;

public class CheckDelaysPresenter implements CheckDelaysOutputBoundary {

    private final CheckDelaysViewModel checkDelaysViewModel;
    private ViewManagerModel viewManagerModel;

    public CheckDelaysPresenter(ViewManagerModel viewManagerModel,
                                CheckDelaysViewModel checkDelaysViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.checkDelaysViewModel = checkDelaysViewModel;
    }
    public void prepareSuccessView(CheckDelaysOutputData response) {
        CheckDelaysState checkDelaysState = checkDelaysViewModel.getState();
        checkDelaysState.setStationID(checkDelaysState.getStationID());
        checkDelaysState.setTime(checkDelaysState.getTime());
        checkDelaysState.setDepartureTime(checkDelaysState.getDepartureTime());
        checkDelaysViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        CheckDelaysState checkDelaysState = checkDelaysViewModel.getState();
        checkDelaysState.setStationIDError(error);
        checkDelaysViewModel.firePropertyChanged();
    }
}
