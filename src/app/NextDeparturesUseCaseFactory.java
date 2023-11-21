package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.next_departures.NextDepartureController;
import interface_adapter.next_departures.NextDeparturePresenter;
import interface_adapter.next_departures.NextDepartureViewModel;
import use_case.next_departures.NextDepartureDataAccessInterface;
import use_case.next_departures.NextDepartureInputBoundary;
import use_case.next_departures.NextDepartureInteractor;
import use_case.next_departures.NextDepartureOutputBoundary;
import view.NextDepartureView;

import javax.swing.*;
import java.io.IOException;

public class NextDeparturesUseCaseFactory {
    private NextDeparturesUseCaseFactory() {}

    public static NextDepartureView create(
            ViewManagerModel viewManagerModel, NextDepartureViewModel nextDepartureViewModel, NextDepartureDataAccessInterface nextDepartureDataAccess) {

        try {
            NextDepartureController nextDepartureController = createNextDepartureUseCase(viewManagerModel, nextDepartureViewModel, nextDepartureDataAccess);
            return new NextDepartureView(nextDepartureController, nextDepartureViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static NextDepartureController createNextDepartureUseCase(ViewManagerModel viewManagerModel, NextDepartureViewModel nextDepartureViewModel, NextDepartureDataAccessInterface nextDepartureDataAccess) throws IOException {

        NextDepartureOutputBoundary nextDepartureOutputBoundary = new NextDeparturePresenter(viewManagerModel, nextDepartureViewModel);

        NextDepartureInputBoundary nextDepartureInteractor = new NextDepartureInteractor(
                nextDepartureDataAccess, nextDepartureOutputBoundary);

        return new NextDepartureController(nextDepartureInteractor);
    }
}
