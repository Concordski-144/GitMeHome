package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.closest_stops.ClosestStopsController;
import interface_adapter.closest_stops.ClosestStopsState;
import interface_adapter.closest_stops.ClosestStopsViewModel;
import interface_adapter.next_departures.NextDeparturesController;
import interface_adapter.next_departures.NextDeparturesState;
import interface_adapter.next_departures.NextDeparturesViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import java.util.List;

public class ClosestStopsView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "closest stops";

    private final ViewManagerModel viewManagerModel;
    private final ClosestStopsViewModel closestStopsViewModel;
    private final ClosestStopsController closestStopsController;
    private final NextDeparturesViewModel nextDeparturesViewModel;
    private final NextDeparturesController nextDeparturesController;
    private final String invalidStopError = "Invalid stop";
    private JButton backToMenu = new JButton("Back");
    private final JLabel stopLabel1 = new JLabel();
    private final JLabel stopLabel2 = new JLabel();
    private final JLabel stopLabel3 = new JLabel();
    private final JLabel stopLabel4 = new JLabel();
    private final JLabel stopLabel5 = new JLabel();
    private final JLabel[] stopLabels = {stopLabel1, stopLabel2, stopLabel3, stopLabel4, stopLabel5};
    private JButton getDeparturesButton1 = new JButton("Get departures");
    private JButton getDeparturesButton2 = new JButton("Get departures");
    private JButton getDeparturesButton3 = new JButton("Get departures");
    private JButton getDeparturesButton4 = new JButton("Get departures");
    private JButton getDeparturesButton5 = new JButton("Get departures");
    private JPanel closestStopsViewPanel;

    public ClosestStopsView(ViewManagerModel viewManagerModel,
                            ClosestStopsController closestStopsController,
                            ClosestStopsViewModel closestStopsViewModel,
                            NextDeparturesViewModel nextDeparturesViewModel,
                            NextDeparturesController nextDeparturesController) {
        this.viewManagerModel = viewManagerModel;
        this.closestStopsController = closestStopsController;
        this.closestStopsViewModel = closestStopsViewModel;
        this.nextDeparturesViewModel = nextDeparturesViewModel;
        this.nextDeparturesController = nextDeparturesController;
        closestStopsViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Nearest stops to user");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panel1 = new JPanel();
        panel1.add(stopLabel1);
        panel1.add(getDeparturesButton1);
        JPanel panel2 = new JPanel();
        panel2.add(stopLabel2);
        panel2.add(getDeparturesButton2);
        JPanel panel3 = new JPanel();
        panel3.add(stopLabel3);
        panel3.add(getDeparturesButton3);
        JPanel panel4 = new JPanel();
        panel4.add(stopLabel4);
        panel4.add(getDeparturesButton4);
        JPanel panel5 = new JPanel();
        panel5.add(stopLabel5);
        panel5.add(getDeparturesButton5);

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
                        String stopId = findStopId(closestStops, 0);
                        NextDeparturesState nextDeparturesState = nextDeparturesViewModel.getState();
                        nextDeparturesState.setStationID(stopId);
                        nextDeparturesController.execute(stopId, nextDeparturesState.getTime(), true);
                        JOptionPane.showMessageDialog(title, nextDeparturesState.toString());
                    } catch (IndexOutOfBoundsException exception) {
                        JOptionPane.showMessageDialog(null, invalidStopError);
                    }
                }
            }
        });

        getDeparturesButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(getDeparturesButton2)) {
                    ClosestStopsState currentState = closestStopsViewModel.getState();
                    try {  // try to initiate the NextDepartures use case
                        HashMap<String, List<Object>> closestStops = currentState.getClosestStops();
                        String stopId = findStopId(closestStops, 1);
                        NextDeparturesState nextDeparturesState = nextDeparturesViewModel.getState();
                        nextDeparturesState.setStationID(stopId);
                        nextDeparturesController.execute(stopId, nextDeparturesState.getTime(), true);
                        JOptionPane.showMessageDialog(title, nextDeparturesState.toString());
                    } catch (IndexOutOfBoundsException exception) {
                        JOptionPane.showMessageDialog(null, invalidStopError);
                    }
                }
            }
        });

        getDeparturesButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(getDeparturesButton3)) {
                    ClosestStopsState currentState = closestStopsViewModel.getState();
                    try {  // try to initiate the NextDepartures use case
                        HashMap<String, List<Object>> closestStops = currentState.getClosestStops();
                        String stopId = findStopId(closestStops, 2);
                        NextDeparturesState nextDeparturesState = nextDeparturesViewModel.getState();
                        nextDeparturesState.setStationID(stopId);
                        nextDeparturesController.execute(stopId, nextDeparturesState.getTime(), true);
                        JOptionPane.showMessageDialog(title, nextDeparturesState.toString());
                    } catch (IndexOutOfBoundsException exception) {
                        JOptionPane.showMessageDialog(null, invalidStopError);
                    }
                }
            }
        });

        getDeparturesButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(getDeparturesButton4)) {
                    ClosestStopsState currentState = closestStopsViewModel.getState();
                    try {  // try to initiate the NextDepartures use case
                        HashMap<String, List<Object>> closestStops = currentState.getClosestStops();
                        String stopId = findStopId(closestStops, 3);
                        NextDeparturesState nextDeparturesState = nextDeparturesViewModel.getState();
                        nextDeparturesState.setStationID(stopId);
                        nextDeparturesController.execute(stopId, nextDeparturesState.getTime(), true);
                        JOptionPane.showMessageDialog(title, nextDeparturesState.toString());
                    } catch (IndexOutOfBoundsException exception) {
                        JOptionPane.showMessageDialog(null, invalidStopError);
                    }
                }
            }
        });

        getDeparturesButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(getDeparturesButton5)) {
                    ClosestStopsState currentState = closestStopsViewModel.getState();
                    try {  // try to initiate the NextDepartures use case
                        HashMap<String, List<Object>> closestStops = currentState.getClosestStops();
                        String stopId = findStopId(closestStops, 4);
                        NextDeparturesState nextDeparturesState = nextDeparturesViewModel.getState();
                        nextDeparturesState.setStationID(stopId);
                        nextDeparturesController.execute(stopId, nextDeparturesState.getTime(), true);
                        JOptionPane.showMessageDialog(title, nextDeparturesState.toString());
                    } catch (IndexOutOfBoundsException exception) {
                        JOptionPane.showMessageDialog(null, invalidStopError);
                    }
                }
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
        this.add(panel5);
        this.add(backToMenu);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    // this sets the display of data (hopefully???)
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ClosestStopsState state = (ClosestStopsState) evt.getNewValue();
        if (state.getClosestStopsError() != null) {
            JOptionPane.showMessageDialog(this, state.getClosestStopsError());
        } else {
            HashMap<String, List<Object>> closestStops = state.getClosestStops();
            ArrayList<List<Object>> closestStopsData = new ArrayList<>(closestStops.values());
            closestStopsData.sort(new Comparator<List<Object>>() {
                @Override
                public int compare(List<Object> o1, List<Object> o2) {
                    return ((Integer) o1.get(1)).compareTo(((Integer) o2.get(1)));
                }
            });
            for (int i = 0; i < closestStopsData.size(); i++) {
                stopLabels[i].setText(stopDataToString((ArrayList<Object>) closestStopsData.get(i)));
                // unchecked cast. we ball
            }
        }
    }

    private String stopDataToString(ArrayList<Object> stopData) {
        HashMap<Integer, String> transitModes = new HashMap<>();
        transitModes.put(0, "Streetcar");
        transitModes.put(1, "Subway");
        transitModes.put(3, "Bus");
        return "<html>" + stopData.get(0) + "<br/>Distance: " + stopData.get(1) + " m<br/>Transit mode: "
                + transitModes.get((int) stopData.get(2)) + "<br/>Accessible: "
                + ((stopData.get(3).equals(0)) ? "No</html>" : "Yes</html>");
    }

    private String findStopId(HashMap<String, List<Object>> closestStops, int index) throws IndexOutOfBoundsException {
        ArrayList<List<Object>> closestStopsData = new ArrayList<>(closestStops.values());
        closestStopsData.sort(new Comparator<List<Object>>() {
            @Override
            public int compare(List<Object> o1, List<Object> o2) {
                return ((Integer) o1.get(1)).compareTo(((Integer) o2.get(1)));
            }
        });
        ArrayList<String> stopId = new ArrayList<>();
        for (List<Object> item : closestStopsData) {
            for (Map.Entry<String, List<Object>> entry : closestStops.entrySet()) {
                if (entry.getValue().equals(item)) {
                    stopId.add(entry.getKey());
                }
            }
        }
        return stopId.get(index);
    }
}
