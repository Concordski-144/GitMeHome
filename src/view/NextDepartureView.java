package view;

import interface_adapter.next_departures.NextDepartureController;
import interface_adapter.next_departures.NextDepartureState;
import interface_adapter.next_departures.NextDepartureViewModel;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class NextDepartureView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "next departure";

    private final NextDepartureViewModel nextDepartureViewModel;
    private final JTextField stationIDInputField = new JTextField(15);
    private final JTextField timeInputField = new JTextField(15);
    private final NextDepartureController nextDepartureController;

    private final JButton nextDeparture;
    private final JButton cancel;

    public NextDepartureView(NextDepartureController controller, NextDepartureViewModel viewModel) {

        this.nextDepartureController = controller;
        this.nextDepartureViewModel = viewModel;
        nextDepartureViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(NextDepartureViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel stationIDInfo = new LabelTextPanel(
                new JLabel(NextDepartureViewModel.STATIONID_LABEL), stationIDInputField);


        JPanel buttons = new JPanel();
        nextDeparture = new JButton(NextDepartureViewModel.NEXT_DEPARTURE_BUTTON_LABEL);
        buttons.add(nextDeparture);
        cancel = new JButton(NextDepartureViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        nextDeparture.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(nextDeparture)) {
                            NextDepartureState currentState = nextDepartureViewModel.getState();

                            nextDepartureController.execute(
                                    currentState.getStationID(), currentState.getTime()
                            );
                        }
                    }
                }
        );


        cancel.addActionListener(this);

        // This makes a new KeyListener implementing class, instantiates it, and
        // makes it listen to keystrokes in the usernameInputField.
        //
        // Notice how it has access to instance variables in the enclosing class!
        stationIDInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        NextDepartureState currentState = nextDepartureViewModel.getState();
                        String text = stationIDInputField.getText() + e.getKeyChar();
                        currentState.setStationID(text);
                        nextDepartureViewModel.setState(currentState);
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
                        NextDepartureState currentState = nextDepartureViewModel.getState();
                        currentState.setTime(timeInputField.getText() + e.getKeyChar());
                        nextDepartureViewModel.setState(currentState);
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
        this.add(buttons);
    }


    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Objects.equals(evt.getPropertyName(), "state")) {
            NextDepartureState state = (NextDepartureState) evt.getNewValue();


        }
    }
}
