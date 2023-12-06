package interface_adapter.closest_stops;

import interface_adapter.ViewManagerModel;
import use_case.closest_stops.ClosestStopsOutputBoundary;
import use_case.closest_stops.ClosestStopsOutputData;

public class ClosestStopsPresenter implements ClosestStopsOutputBoundary {

    private final ClosestStopsViewModel closestStopsViewModel;
    private ViewManagerModel viewManagerModel;

    public ClosestStopsPresenter(ClosestStopsViewModel closestStopsViewModel, ViewManagerModel viewManagerModel) {
        this.closestStopsViewModel = closestStopsViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ClosestStopsOutputData response) {
        ClosestStopsState closestStopsState = closestStopsViewModel.getState();
        closestStopsState.setClosestStops(response.getStops());
        closestStopsViewModel.firePropertyChanged();
        // On success switch to ClosestStopsView
        viewManagerModel.setActiveView(closestStopsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        ClosestStopsState closestStopsState = closestStopsViewModel.getState();
        closestStopsState.setClosestStopsError(error);
        closestStopsViewModel.firePropertyChanged();
        // On failure show error instead of info
        viewManagerModel.setActiveView(closestStopsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
