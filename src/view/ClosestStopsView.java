package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.closest_stops.ClosestStopsController;
import interface_adapter.closest_stops.ClosestStopsState;
import interface_adapter.closest_stops.ClosestStopsViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClosestStopsView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "closest stops";

    private final ViewManagerModel viewManagerModel;
    private final ClosestStopsViewModel closestStopsViewModel;
    private final ClosestStopsController closestStopsController;
    private final String invalidStopError = "Invalid stop";
    private JButton backToMenu;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JTextArea textArea4;
    private JTextArea textArea5;
    private final JTextArea[] textAreas = {textArea1, textArea2, textArea3, textArea4, textArea5};
    private JLabel viewNameLabel;
    private JButton getDeparturesButton1;
    private JButton getDeparturesButton2;
    private JButton getDeparturesButton4;
    private JButton getDeparturesButton5;
    private JButton getDeparturesButton3;
    private JPanel closestStopsViewPanel;

    public ClosestStopsView(ViewManagerModel viewManagerModel, ClosestStopsController closestStopsController,
                            ClosestStopsViewModel closestStopsViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.closestStopsController = closestStopsController;
        this.closestStopsViewModel = closestStopsViewModel;
        closestStopsViewModel.addPropertyChangeListener(this);

        // interactive buttons
        backToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(backToMenu)) {
                    viewManagerModel.setActiveView("main menu");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });
        getDeparturesButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(getDeparturesButton1)) {
                    ClosestStopsState currentState = closestStopsViewModel.getState();
                    try {  // try to initiate the NextDepartures use case
                        HashMap<String, List<Object>> closestStops = currentState.getClosestStops();
                        ArrayList<String> closestStopsId = new ArrayList<>(closestStops.keySet());
                        // idea: get station id; if there is no corresponding station id for the index it is caught
                        // TODO: 2023-11-29 implement this for all buttons!
                    } catch (IndexOutOfBoundsException exception) {
                        JOptionPane.showMessageDialog(null, invalidStopError);
                    }
                }
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    // this sets the display of data
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ClosestStopsState state = (ClosestStopsState) evt.getNewValue();
        if (state.getClosestStopsError() != null) {
            JOptionPane.showMessageDialog(this, state.getClosestStopsError());
        } else {
            HashMap<String, List<Object>> closestStops = state.getClosestStops();
            ArrayList<Object> closestStopsData = new ArrayList<>(closestStops.values());
            for (int i = 0; i < closestStopsData.size(); i++) {
                textAreas[i].append(stopDataToString((ArrayList<Object>) closestStopsData.get(i)));
                // unchecked cast. we ball
            }
        }
    }

    public String stopDataToString(ArrayList<Object> stopData) {
        HashMap<Integer, String> transitModes = new HashMap<>();
        transitModes.put(0, "Streetcar");
        transitModes.put(1, "Subway");
        transitModes.put(3, "Bus");
        return stopData.get(0) + "\nDistance: " + stopData.get(1) + " m\nTransit mode: "
                + transitModes.get((int) stopData.get(2)) + "\nAccessible: "
                + (((int) stopData.get(3) != 0) ? "Yes" : "No");
    }
}
