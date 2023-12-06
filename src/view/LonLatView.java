package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.closest_stops.ClosestStopsController;
import interface_adapter.closest_stops.ClosestStopsState;
import interface_adapter.closest_stops.ClosestStopsViewModel;
import interface_adapter.plan_a_trip.PlanATripViewModel;

import javax.swing.*;
import java.awt.*;
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
    private JTextField lonInputField = new JTextField(15);
    private JTextField latInputField = new JTextField(15);
    private JButton findNearbyStopsButton;
    private JButton cancelButton;

    public LonLatView(ViewManagerModel viewManagerModel, ClosestStopsController closestStopsController,
                      ClosestStopsViewModel closestStopsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.closestStopsController = closestStopsController;
        this.closestStopsViewModel = closestStopsViewModel;
        this.closestStopsViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Enter coordinates");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel lonPanel = new LabelTextPanel(
                new JLabel("Longitude"), lonInputField);

        LabelTextPanel latPanel = new LabelTextPanel(
                new JLabel("Latitude"), latInputField);

        JPanel buttons = new JPanel();
        findNearbyStopsButton = new JButton("Find nearby stops");
        buttons.add(findNearbyStopsButton);
        cancelButton = new JButton("Cancel");
        buttons.add(cancelButton);

        lonInputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                ClosestStopsState currentState = closestStopsViewModel.getState();
                currentState.setLon(lonInputField.getText() + e.getKeyChar());
                closestStopsViewModel.setState(currentState);
            }
        });

        latInputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                ClosestStopsState currentState = closestStopsViewModel.getState();
                currentState.setLat(latInputField.getText() + e.getKeyChar());
                closestStopsViewModel.setState(currentState);
            }
        });

        findNearbyStopsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(findNearbyStopsButton)) {
                    ClosestStopsState currentState = closestStopsViewModel.getState();
                    closestStopsController.execute(Double.parseDouble(currentState.getLon()), Double.parseDouble(currentState.getLat()), 5);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(cancelButton)) {
                    viewManagerModel.setActiveView("main menu");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(lonPanel);
        this.add(latPanel);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
