package view;

import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainMenuView  extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "main menu";
    private final ViewManagerModel viewManagerModel;

    private final JButton planATrip;
    private final JButton nextDeparture;
    private final JButton checkDelays;
    private final JButton closestStops;
    private final JButton cancel;
    private final JButton getDetails;

    public MainMenuView(ViewManagerModel viewManagerModel) {

        JLabel title = new JLabel("TTC Tracker");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.viewManagerModel = viewManagerModel;

        JPanel buttons = new JPanel();
        nextDeparture = new JButton("Find Next Departure of a stop");
        buttons.add(nextDeparture);
        planATrip = new JButton("Plan a trip");
        buttons.add(planATrip);
        checkDelays = new JButton("Check for delays");
        buttons.add(checkDelays);
        closestStops = new JButton("Find closest stops to your location");
        buttons.add(closestStops);
        getDetails = new JButton("Get details of a route");
        buttons.add(getDetails);
        cancel = new JButton("Cancel");
        buttons.add(cancel);

        buttons.setLayout(new GridLayout(5, 0));

        nextDeparture.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(nextDeparture)) {
                            viewManagerModel.setActiveView("next departure");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        planATrip.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(planATrip)) {
                            viewManagerModel.setActiveView("plan a trip");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        checkDelays.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(checkDelays)) {
                            viewManagerModel.setActiveView("check delays");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        closestStops.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(closestStops)) {
                            viewManagerModel.setActiveView("user location");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        getDetails.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(getDetails)){
                            viewManagerModel.setActiveView("get details");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        cancel.addActionListener(
                new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    if (evt.getSource().equals(cancel)) {
                        System.exit(0);
                    }
                }
            }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(buttons);
    }


    public void actionPerformed(ActionEvent evt) {
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}
