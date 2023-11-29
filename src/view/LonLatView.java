package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.closest_stops.ClosestStopsController;
import interface_adapter.closest_stops.ClosestStopsState;
import interface_adapter.closest_stops.ClosestStopsViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LonLatView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "user location";

    private ViewManagerModel viewManagerModel;
    private ClosestStopsController closestStopsController;
    private ClosestStopsViewModel closestStopsViewModel;
    private JTextField lonInputField;
    private JTextField latInputField;
    private JPanel panel;
    private JButton findNearbyStopsButton;
    private JButton ABORTButton;

    public LonLatView(ViewManagerModel viewManagerModel, ClosestStopsController closestStopsController,
                      ClosestStopsViewModel closestStopsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.closestStopsController = closestStopsController;
        this.closestStopsViewModel = closestStopsViewModel;
        this.closestStopsViewModel.addPropertyChangeListener(this);

        lonInputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                ClosestStopsState currentState = closestStopsViewModel.getState();
                currentState.setLon(Double.parseDouble(lonInputField.getText() + e.getKeyChar()));
                closestStopsViewModel.setState(currentState);
            }
        });

        latInputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                ClosestStopsState currentState = closestStopsViewModel.getState();
                currentState.setLat(Double.parseDouble(latInputField.getText() + e.getKeyChar()));
                closestStopsViewModel.setState(currentState);
            }
        });

        findNearbyStopsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(findNearbyStopsButton)) {
                    ClosestStopsState currentState = closestStopsViewModel.getState();
                    closestStopsController.execute(currentState.getLon(), currentState.getLat(), 5);
                    // this should hopefully change to the ClosestStopsView
                }
            }
        });

        ABORTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setActiveView("main menu");
                viewManagerModel.firePropertyChanged();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
