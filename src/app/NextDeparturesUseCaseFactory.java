package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.next_departures.NextDeparturesController;
import interface_adapter.next_departures.NextDeparturesPresenter;
import interface_adapter.next_departures.NextDeparturesViewModel;
import use_case.next_departures.NextDeparturesDataAccessInterface;
import use_case.next_departures.NextDeparturesInputBoundary;
import use_case.next_departures.NextDeparturesInteractor;
import use_case.next_departures.NextDeparturesOutputBoundary;
import view.NextDeparturesView;

import javax.swing.*;
import java.io.IOException;

public class NextDeparturesUseCaseFactory {
    private NextDeparturesUseCaseFactory() {}

    public static NextDeparturesView create(
            ViewManagerModel viewManagerModel, NextDeparturesViewModel nextDepartureViewModel, NextDeparturesDataAccessInterface nextDepartureDataAccess) {

        try {
            NextDeparturesController nextDepartureController = createNextDepartureUseCase(viewManagerModel, nextDepartureViewModel, nextDepartureDataAccess);
            return new NextDeparturesView(viewManagerModel, nextDepartureController, nextDepartureViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static NextDeparturesController createNextDepartureUseCase(ViewManagerModel viewManagerModel, NextDeparturesViewModel nextDepartureViewModel, NextDeparturesDataAccessInterface nextDepartureDataAccess) throws IOException {

        NextDeparturesOutputBoundary nextDepartureOutputBoundary = new NextDeparturesPresenter(viewManagerModel, nextDepartureViewModel);

        NextDeparturesInputBoundary nextDepartureInteractor = new NextDeparturesInteractor(
                nextDepartureDataAccess, nextDepartureOutputBoundary);

        return new NextDeparturesController(nextDepartureInteractor);
    }
}
