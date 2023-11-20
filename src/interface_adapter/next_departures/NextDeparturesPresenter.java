package interface_adapter.next_departures;

import interface_adapter.ViewManagerModel;
import interface_adapter.next_departures.NextDepartureState;
import interface_adapter.next_departures.NextDepartureViewModel;
import use_case.next_departures.NextDepartureOutputBoundary;
import use_case.next_departures.NextDepartureOutputBoundary;
import use_case.next_departures.NextDepartureOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NextDeparturePresenter implements NextDepartureOutputBoundary {

    private final NextDepartureViewModel nextDepartureViewModel;
    private ViewManagerModel viewManagerModel;

    public NextDeparturePresenter(ViewManagerModel viewManagerModel,
                                  NextDepartureViewModel nextDepartureViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.nextDepartureViewModel = nextDepartureViewModel;
    }


    public void prepareSuccessView(NextDepartureOutputData response) {
        NextDepartureState nextDepartureState = nextDepartureViewModel.getState();
        nextDepartureState.setStationID(nextDepartureState.getStationID());
        nextDepartureState.setTime(nextDepartureState.getTime());
        nextDepartureState.setDepartureTime(nextDepartureState.getDepartureTime());
        nextDepartureViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        NextDepartureState nextDepartureState = nextDepartureViewModel.getState();
        nextDepartureState.setStationIDError(error);
        nextDepartureViewModel.firePropertyChanged();
    }
}
