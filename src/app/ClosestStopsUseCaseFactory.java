package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.closest_stops.ClosestStopsController;
import interface_adapter.closest_stops.ClosestStopsPresenter;
import interface_adapter.closest_stops.ClosestStopsViewModel;
import use_case.closest_stops.ClosestStopsDataAccessInterface;
import use_case.closest_stops.ClosestStopsInputBoundary;
import use_case.closest_stops.ClosestStopsInteractor;
import use_case.closest_stops.ClosestStopsOutputBoundary;
import view.ClosestStopsView;
import view.LonLatView;

import javax.swing.*;

public class ClosestStopsUseCaseFactory {

    private ClosestStopsUseCaseFactory() {}

    public static ClosestStopsView createClosestStopsView(
            ViewManagerModel viewManagerModel,
            ClosestStopsViewModel closestStopsViewModel,
            ClosestStopsDataAccessInterface closestStopsDataAccessObject) {
        try {
            ClosestStopsController closestStopsController = createClosestStopsUseCase(viewManagerModel, closestStopsViewModel, closestStopsDataAccessObject);
            return new ClosestStopsView(viewManagerModel, closestStopsController, closestStopsViewModel);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }

    public static LonLatView createLonLatView(
            ViewManagerModel viewManagerModel,
            ClosestStopsViewModel closestStopsViewModel,
            ClosestStopsDataAccessInterface closestStopsDataAccessObject) {
        try {
            ClosestStopsController closestStopsController = createClosestStopsUseCase(viewManagerModel, closestStopsViewModel, closestStopsDataAccessObject);
            return new LonLatView(viewManagerModel, closestStopsController, closestStopsViewModel);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return null;
    }
    // TODO: 2023-11-29 find how to implement two views without breaking CA and SOLID


    public static ClosestStopsController createClosestStopsUseCase(
            ViewManagerModel viewManagerModel,
            ClosestStopsViewModel closestStopsViewModel,
            ClosestStopsDataAccessInterface closestStopsDataAccessObject) throws RuntimeException {
        ClosestStopsOutputBoundary closestStopsOutputBoundary = new ClosestStopsPresenter(closestStopsViewModel, viewManagerModel);
        ClosestStopsInputBoundary closestStopsInteractor = new ClosestStopsInteractor(closestStopsDataAccessObject, closestStopsOutputBoundary);
        return new ClosestStopsController(closestStopsInteractor);
    }
}
