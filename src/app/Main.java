package app;

import data_access.ClosestStopsDataAccessObject;
import data_access.GetDetailsDataAccessObject;
import data_access.NextDeparturesDataAccessObject;
import data_access.CheckDelaysDataAccessObject;
import data_access.PlanATripDataAccessObject;

import interface_adapter.ViewManagerModel;
import interface_adapter.closest_stops.ClosestStopsViewModel;
import interface_adapter.get_details.GetDetailsViewModel;
import interface_adapter.next_departures.NextDeparturesViewModel;
import interface_adapter.check_delays.CheckDelaysViewModel;
import interface_adapter.plan_a_trip.PlanATripViewModel;

import use_case.closest_stops.ClosestStopsDataAccessInterface;
import use_case.get_details.GetDetailsDataAccessInterface;
import use_case.next_departures.NextDeparturesDataAccessInterface;
import use_case.check_delays.CheckDelaysDataAccessInterface;
import use_case.plan_a_trip.PlanATripDataAccessInterface;

import view.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame application = new JFrame("TTC Tracker");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        NextDeparturesViewModel nextDepartureViewModel = new NextDeparturesViewModel();
        PlanATripViewModel planATripViewModel = new PlanATripViewModel();
        CheckDelaysViewModel checkDelaysViewModel = new CheckDelaysViewModel();
        ClosestStopsViewModel closestStopsViewModel = new ClosestStopsViewModel();
        GetDetailsViewModel getDetailsViewModel = new GetDetailsViewModel();

        NextDeparturesDataAccessInterface nextDepartureDataAccessInterface = new NextDeparturesDataAccessObject();
        PlanATripDataAccessInterface planATripDataAccessInterface = new PlanATripDataAccessObject();
        CheckDelaysDataAccessInterface checkDelaysDataAccessInterface = new CheckDelaysDataAccessObject();
        ClosestStopsDataAccessInterface closestStopsDataAccessInterface = new ClosestStopsDataAccessObject();
        GetDetailsDataAccessInterface getDetailsDataAccessInterface = new GetDetailsDataAccessObject();

        NextDeparturesView nextDepartureView = NextDeparturesUseCaseFactory.create(viewManagerModel, nextDepartureViewModel, nextDepartureDataAccessInterface);
        views.add(nextDepartureView, nextDepartureView.viewName);

        PlanATripView planATripView = PlanATripUseCaseFactory.create(viewManagerModel, planATripViewModel, planATripDataAccessInterface);
        views.add(planATripView, planATripView.viewName);

        CheckDelaysView checkDelaysView = CheckDelaysUseCaseFactory.create(viewManagerModel, checkDelaysViewModel, checkDelaysDataAccessInterface);
        views.add(checkDelaysView, checkDelaysView.viewName);

        ClosestStopsView closestStopsView = ClosestStopsUseCaseFactory.createClosestStopsView(
                viewManagerModel, closestStopsViewModel, closestStopsDataAccessInterface, nextDepartureViewModel, nextDepartureDataAccessInterface);
        views.add(closestStopsView, closestStopsView.viewName);

        GetDetailsView getDetailsView = GetDetailsUseCaseFactory.create(viewManagerModel, getDetailsViewModel, getDetailsDataAccessInterface);
        views.add(getDetailsView, getDetailsView.viewName);

        MainMenuView mainMenuView = new MainMenuView(viewManagerModel);
        views.add(mainMenuView, mainMenuView.viewName);

        viewManagerModel.setActiveView(mainMenuView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
