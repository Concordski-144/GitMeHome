package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.check_delays.CheckDelaysController;
import interface_adapter.check_delays.CheckDelaysPresenter;
import interface_adapter.check_delays.CheckDelaysViewModel;
import use_case.check_delays.CheckDelaysDataAccessInterface;
import use_case.check_delays.CheckDelaysInputBoundary;
import use_case.check_delays.CheckDelaysInteractor;
import use_case.check_delays.CheckDelaysOutputBoundary;
import view.CheckDelaysView;

import javax.swing.*;
import java.io.IOException;

public class CheckDelaysUseCaseFactory {
    private CheckDelaysUseCaseFactory() {}

    public static CheckDelaysView create(
            ViewManagerModel viewManagerModel, CheckDelaysViewModel checkDelaysViewModel, CheckDelaysDataAccessInterface checkDelaysDataAccess) {

        try {
            CheckDelaysController checkDelaysController = createNextDepartureUseCase(viewManagerModel, checkDelaysViewModel, checkDelaysDataAccess);
            return new CheckDelaysView(viewManagerModel, checkDelaysController, checkDelaysViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: cannot initialize check delays use case.");
        }

        return null;
    }

    private static CheckDelaysController createNextDepartureUseCase(ViewManagerModel viewManagerModel, CheckDelaysViewModel checkDelaysViewModel, CheckDelaysDataAccessInterface checkDelaysDataAccess) throws IOException {

        CheckDelaysOutputBoundary checkDelaysOutputBoundary = new CheckDelaysPresenter(viewManagerModel, checkDelaysViewModel);

        CheckDelaysInputBoundary checkDelaysInteractor = new CheckDelaysInteractor(
                checkDelaysDataAccess, checkDelaysOutputBoundary);

        return new CheckDelaysController(checkDelaysInteractor);
    }
}
