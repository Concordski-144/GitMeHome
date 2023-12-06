package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.get_details.GetDetailsController;
import interface_adapter.get_details.GetDetailsPresenter;
import interface_adapter.get_details.GetDetailsViewModel;
import use_case.get_details.GetDetailsDataAccessInterface;
import use_case.get_details.GetDetailsInputBoundary;
import use_case.get_details.GetDetailsInteractor;
import use_case.get_details.GetDetailsOutputBoundary;
import view.GetDetailsView;


import javax.swing.*;
import java.io.IOException;

public class GetDetailsUseCaseFactory {
    public static GetDetailsView create(
            ViewManagerModel viewManagerModel, GetDetailsViewModel getDetailsViewModel, GetDetailsDataAccessInterface getDetailsDataAccess) {

        try {
            GetDetailsController getDetailsController = createGetDetailsUseCase(viewManagerModel, getDetailsViewModel, getDetailsDataAccess);
            return new GetDetailsView(getDetailsController, getDetailsViewModel, viewManagerModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static GetDetailsController createGetDetailsUseCase(ViewManagerModel viewManagerModel, GetDetailsViewModel getDetailsViewModel, GetDetailsDataAccessInterface getDetailsDataAccess) throws IOException {

        GetDetailsOutputBoundary getDetailsOutputBoundary = new GetDetailsPresenter(viewManagerModel, getDetailsViewModel);

        GetDetailsInputBoundary getDetailsInteractor = new GetDetailsInteractor(
                getDetailsDataAccess, getDetailsOutputBoundary);

        return new GetDetailsController(getDetailsInteractor);
    }
}
