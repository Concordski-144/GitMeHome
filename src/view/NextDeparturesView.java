package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.next_departures.NextDeparturesController;
import interface_adapter.next_departures.NextDeparturesState;
import interface_adapter.next_departures.NextDeparturesViewModel;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class NextDeparturesView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "next departure";

    private final ViewManagerModel viewManagerModel;
    private final NextDeparturesViewModel nextDeparturesViewModel;
    private final JTextField stationIDInputField = new JTextField(15);
    private final JTextField timeInputField = new JTextField(15);
    private final NextDeparturesController nextDeparturesController;

    private final JButton nextDeparture;
    private final JButton cancel;

    public NextDeparturesView(ViewManagerModel viewManagerModel, NextDeparturesController controller, NextDeparturesViewModel viewModel) {
        this.viewManagerModel = viewManagerModel;
        this.nextDeparturesController = controller;
        this.nextDeparturesViewModel = viewModel;
        nextDeparturesViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(NextDeparturesViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel stationIDInfo = new LabelTextPanel(
                new JLabel(NextDeparturesViewModel.STATIONID_LABEL), stationIDInputField);


        LabelTextPanel timeInfo = new LabelTextPanel(
                new JLabel(NextDeparturesViewModel.TIME_LABEL), timeInputField);


        JPanel buttons = new JPanel();
        nextDeparture = new JButton(NextDeparturesViewModel.NEXT_DEPARTURE_BUTTON_LABEL);
        buttons.add(nextDeparture);
        cancel = new JButton(NextDeparturesViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        nextDeparture.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(nextDeparture)) {
                            NextDeparturesState currentState = nextDeparturesViewModel.getState();
                            nextDeparturesController.execute(
                                    currentState.getStationID(), currentState.getTime(), timeInputField.getText().isEmpty()
                            );
                            JOptionPane.showMessageDialog(title, currentState.toString());
                        }
                    }
                }
        );


        cancel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(cancel)) {
                            viewManagerModel.setActiveView("main menu");
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        // This makes a new KeyListener implementing class, instantiates it, and
        // makes it listen to keystrokes in the usernameInputField.
        //
        // Notice how it has access to instance variables in the enclosing class!
        stationIDInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        NextDeparturesState currentState = nextDeparturesViewModel.getState();
                        String text = stationIDInputField.getText() + e.getKeyChar();
                        currentState.setStationID(text);
                        nextDeparturesViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        timeInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        NextDeparturesState currentState = nextDeparturesViewModel.getState();
                        currentState.setTime(timeInputField.getText() + e.getKeyChar());
                        nextDeparturesViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(stationIDInfo);
        this.add(timeInfo);
        this.add(buttons);
    }


    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Objects.equals(evt.getPropertyName(), "state")) {
            NextDeparturesState state = (NextDeparturesState) evt.getNewValue();
        }
    }
}