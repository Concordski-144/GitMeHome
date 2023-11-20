package interface_adapter.next_departures;

import interface_adapter.ViewManagerModel;
import use_case.next_departures.NextDepartureOutputData;
import use_case.next_departures.NextDeparturesOutputBoundary;

public class NextDeparturesPresenter implements NextDeparturesOutputBoundary {

    private final NextDeparturesViewModel nextDeparturesViewModel;

    private ViewManagerModel ViewManagerModel;

    public NextDeparturesPresenter (ViewManagerModel viewManagerModel,
                                    NextDeparturesViewModel nextDeparturesViewModel) {
        this.nextDeparturesViewModel = nextDeparturesViewModel;

    }

    @Override
    public void prepareSuccessView(NextDepartureOutputData station) {
        // once you get a good station id then get the next departures view
        NextDeparturesViewModel.getNextDepartures(station);
    }

    @Override
    public void prepareFailView(NextDepartureOutputData error) {
        NextDeparturesState nextDeparturesState = NextDeparturesViewModel.getState();
        NextDeparturesViewModel.getDeparturesError(error);
    }
}
