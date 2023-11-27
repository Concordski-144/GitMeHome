package interface_adapter.closest_stops;

import interface_adapter.ViewManagerModel;
import use_case.closest_stops.ClosestStopsOutputBoundary;
import use_case.closest_stops.ClosestStopsOutputData;

public class ClosestStopsPresenter implements ClosestStopsOutputBoundary {

    private final ClosestStopsViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;

    public ClosestStopsPresenter(ClosestStopsViewModel loginViewModel, ViewManagerModel viewManagerModel) {
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ClosestStopsOutputData response) {
        // On success pop out info panel
        // TODO: 2023-11-26 implement this
    }

    @Override
    public void prepareFailView(String error) {
        // TODO: 2023-11-26 implement this
    }
}
