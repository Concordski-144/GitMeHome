package interface_adapter.next_departures;

import interface_adapter.ViewManagerModel;
import use_case.next_departures.NextDeparturesOutputBoundary;
import use_case.next_departures.NextDeparturesOutputData;

public class NextDeparturesPresenter implements NextDeparturesOutputBoundary {

    private final NextDeparturesViewModel nextDeparturesViewModel;
    private ViewManagerModel viewManagerModel;

    public NextDeparturesPresenter(ViewManagerModel viewManagerModel,
                                   NextDeparturesViewModel nextDeparturesViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.nextDeparturesViewModel = nextDeparturesViewModel;
    }
    public void prepareSuccessView(NextDeparturesOutputData response) {
        NextDeparturesState nextDeparturesState = nextDeparturesViewModel.getState();
        nextDeparturesState.setStationID(nextDeparturesState.getStationID());
        nextDeparturesState.setTime(nextDeparturesState.getTime());
        nextDeparturesState.setDepartureTime(response.getDeparturesByRoute());
        nextDeparturesViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        NextDeparturesState nextDeparturesState = nextDeparturesViewModel.getState();
        nextDeparturesState.setStationIDError(error);
        nextDeparturesViewModel.firePropertyChanged();
    }
}
