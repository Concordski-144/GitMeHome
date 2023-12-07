package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.closest_stops.ClosestStopsController;
import interface_adapter.closest_stops.ClosestStopsPresenter;
import interface_adapter.closest_stops.ClosestStopsViewModel;
import interface_adapter.next_departures.NextDeparturesController;
import interface_adapter.next_departures.NextDeparturesPresenter;
import interface_adapter.next_departures.NextDeparturesViewModel;
import use_case.closest_stops.ClosestStopsDataAccessInterface;
import use_case.closest_stops.ClosestStopsInputBoundary;
import use_case.closest_stops.ClosestStopsInteractor;
import use_case.closest_stops.ClosestStopsOutputBoundary;
import use_case.next_departures.NextDeparturesDataAccessInterface;
import use_case.next_departures.NextDeparturesInputBoundary;
import use_case.next_departures.NextDeparturesInteractor;
import use_case.next_departures.NextDeparturesOutputBoundary;
import view.ClosestStopsView;
import view.LonLatView;

import javax.swing.*;

public class ClosestStopsUseCaseFactory {

    private ClosestStopsUseCaseFactory() {}

    public static ClosestStopsView createClosestStopsView(
            ViewManagerModel viewManagerModel,
            ClosestStopsViewModel closestStopsViewModel,
            ClosestStopsDataAccessInterface closestStopsDataAccessObject,
            NextDeparturesViewModel nextDeparturesViewModel,
            NextDeparturesDataAccessInterface nextDeparturesDataAccessObject) {
        try {
            ClosestStopsController closestStopsController = createClosestStopsUseCase(viewManagerModel, closestStopsViewModel, closestStopsDataAccessObject);
            NextDeparturesController nextDeparturesController = createNextDeparturesUseCase(viewManagerModel, nextDeparturesViewModel, nextDeparturesDataAccessObject);
            return new ClosestStopsView(viewManagerModel, closestStopsController, closestStopsViewModel, nextDeparturesViewModel, nextDeparturesController);
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

    private static ClosestStopsController createClosestStopsUseCase(
            ViewManagerModel viewManagerModel,
            ClosestStopsViewModel closestStopsViewModel,
            ClosestStopsDataAccessInterface closestStopsDataAccessObject) throws RuntimeException {
        ClosestStopsOutputBoundary closestStopsOutputBoundary = new ClosestStopsPresenter(closestStopsViewModel, viewManagerModel);
        ClosestStopsInputBoundary closestStopsInteractor = new ClosestStopsInteractor(closestStopsDataAccessObject, closestStopsOutputBoundary);
        return new ClosestStopsController(closestStopsInteractor);
    }

    private static NextDeparturesController createNextDeparturesUseCase(
            ViewManagerModel viewManagerModel,
            NextDeparturesViewModel nextDeparturesViewModel,
            NextDeparturesDataAccessInterface nextDeparturesDataAccessObject) throws RuntimeException {
        NextDeparturesOutputBoundary nextDeparturesOutputBoundary = new NextDeparturesPresenter(viewManagerModel, nextDeparturesViewModel);
        NextDeparturesInputBoundary nextDeparturesInteractor = new NextDeparturesInteractor(nextDeparturesDataAccessObject, nextDeparturesOutputBoundary);
        return new NextDeparturesController(nextDeparturesInteractor);
    }
}
