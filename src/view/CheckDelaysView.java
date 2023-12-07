package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.check_delays.CheckDelaysController;
import interface_adapter.check_delays.CheckDelaysState;
import interface_adapter.check_delays.CheckDelaysViewModel;

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

public class CheckDelaysView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "check delays";

    private final ViewManagerModel viewManagerModel;
    private final CheckDelaysViewModel checkDelaysViewModel;
    private final JTextField stationIDInputField = new JTextField(15);
    private final CheckDelaysController checkDelaysController;

    private final JButton checkDelays;  // by stationID
    private final JButton checkDelaysByRoute;
    private final JButton cancel;

    public CheckDelaysView(ViewManagerModel viewManagerModel, CheckDelaysController controller, CheckDelaysViewModel viewModel) {
        this.viewManagerModel = viewManagerModel;
        this.checkDelaysController = controller;
        this.checkDelaysViewModel = viewModel;
        checkDelaysViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(CheckDelaysViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel stationIDInfo = new LabelTextPanel(
                new JLabel(CheckDelaysViewModel.STATIONID_LABEL), stationIDInputField);


        JPanel buttons = new JPanel();
        checkDelays = new JButton(CheckDelaysViewModel.CHECK_DELAYS_BUTTON_LABEL);
        buttons.add(checkDelays);

        checkDelaysByRoute = new JButton(CheckDelaysViewModel.CHECK_DELAYS_BY_ROUTE_BUTTON_LABEL);
        buttons.add(checkDelaysByRoute);
        
        cancel = new JButton(CheckDelaysViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        checkDelays.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(checkDelays)) {
                            CheckDelaysState currentState = checkDelaysViewModel.getState();
                            currentState.setQueryType("station");
                            checkDelaysController.execute(
                                    currentState.getStationID(), currentState.getType()
                            );
                            JOptionPane.showMessageDialog(title, currentState.toString());
                        }
                    }
                }
        );

        checkDelaysByRoute.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(checkDelaysByRoute)) {
                            CheckDelaysState currentState = checkDelaysViewModel.getState();
                            currentState.setQueryType("route");
                            checkDelaysController.execute(
                                    currentState.getStationID(), currentState.getType()
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
                        CheckDelaysState currentState = checkDelaysViewModel.getState();
                        String text = stationIDInputField.getText() + e.getKeyChar();
                        currentState.setStationID(text);
                        checkDelaysViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });


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
            CheckDelaysState state = (CheckDelaysState) evt.getNewValue();


        }
    }
}