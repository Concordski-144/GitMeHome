package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.plan_a_trip.PlanATripController;
import interface_adapter.plan_a_trip.PlanATripPresenter;
import interface_adapter.plan_a_trip.PlanATripViewModel;
import use_case.plan_a_trip.PlanATripDataAccessInterface;
import use_case.plan_a_trip.PlanATripInputBoundary;
import use_case.plan_a_trip.PlanATripInteractor;
import use_case.plan_a_trip.PlanATripOutputBoundary;
import view.PlanATripView;

import javax.swing.*;
import java.io.IOException;

public class PlanATripUseCaseFactory {
    private PlanATripUseCaseFactory() {}

    public static PlanATripView create(
            ViewManagerModel viewManagerModel, PlanATripViewModel planATripViewModel, PlanATripDataAccessInterface planATripDataAccess) {

        try {
            PlanATripController planATripController = createPlanATripUseCase(viewManagerModel, planATripViewModel, planATripDataAccess);
            return new PlanATripView(planATripController, planATripViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static PlanATripController createPlanATripUseCase(ViewManagerModel viewManagerModel, PlanATripViewModel planATripViewModel, PlanATripDataAccessInterface planATripDataAccess) throws IOException {

        PlanATripOutputBoundary planATripOutputBoundary = new PlanATripPresenter(viewManagerModel, planATripViewModel);

        PlanATripInputBoundary planATripInteractor = new PlanATripInteractor(
                planATripDataAccess, planATripOutputBoundary);

        return new PlanATripController(planATripInteractor);
    }
}
