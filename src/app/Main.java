package app;

import data_access.NextDepartureDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.next_departures.NextDepartureViewModel;
import use_case.next_departures.NextDepartureDataAccessInterface;
import view.NextDepartureView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        JFrame application = new JFrame("TTC Tracker");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        NextDepartureViewModel nextDepartureViewModel = new NextDepartureViewModel();

        NextDepartureDataAccessInterface nextDepartureDataAccessInterface = new NextDepartureDataAccessObject();

        NextDepartureView nextDepartureView = NextDeparturesUseCaseFactory.create(viewManagerModel, nextDepartureViewModel, nextDepartureDataAccessInterface);
        views.add(nextDepartureView, nextDepartureView.viewName);

        viewManagerModel.setActiveView(nextDepartureView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
