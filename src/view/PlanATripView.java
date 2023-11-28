package view;

import interface_adapter.plan_a_trip.PlanATripController;
import interface_adapter.plan_a_trip.PlanATripState;
import interface_adapter.plan_a_trip.PlanATripViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class PlanATripView  extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "plan a trip";

    private final PlanATripViewModel planATripViewModel;
    private final JTextField fromPlaceInputField = new JTextField(15);
    private final JTextField toPlaceInputField = new JTextField(15);
    private final PlanATripController planATripController;

    private final JButton planATrip;
    private final JButton cancel;

    public PlanATripView(PlanATripController planATripController, PlanATripViewModel planATripViewModel) {
        this.planATripViewModel = planATripViewModel;
        this.planATripController = planATripController;
        this.planATripViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(PlanATripViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel fromPlaceInfo = new LabelTextPanel(
                new JLabel(PlanATripViewModel.FROM_PLACE_LABEL), fromPlaceInputField);

        LabelTextPanel toPlaceInfo = new LabelTextPanel(
                new JLabel(PlanATripViewModel.TO_PLACE_LABEL), toPlaceInputField);


        JPanel buttons = new JPanel();
        planATrip = new JButton(PlanATripViewModel.PLAN_A_TRIP_BUTTON_LABEL);
        buttons.add(planATrip);
        cancel = new JButton(PlanATripViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        planATrip.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(planATrip)) {
                            PlanATripState currentState = planATripViewModel.getState();

                            planATripController.execute(
                                    currentState.getFromPlace(), currentState.getToPlace()
                            );
                            JOptionPane.showMessageDialog(title, currentState.getPlanMap());
                        }
                    }
                }
        );


        cancel.addActionListener(this);

        // This makes a new KeyListener implementing class, instantiates it, and
        // makes it listen to keystrokes in the usernameInputField.
        //
        // Notice how it has access to instance variables in the enclosing class!
        fromPlaceInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        PlanATripState currentState = planATripViewModel.getState();
                        String text = fromPlaceInputField.getText() + e.getKeyChar();
                        currentState.setFromPlace(text);
                        planATripViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        toPlaceInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        PlanATripState currentState = planATripViewModel.getState();
                        String text = toPlaceInputField.getText() + e.getKeyChar();
                        currentState.setToPlace(text);
                        planATripViewModel.setState(currentState);
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
        this.add(fromPlaceInfo);
        this.add(toPlaceInfo);
        this.add(buttons);
    }


    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Objects.equals(evt.getPropertyName(), "state")) {
            PlanATripState state = (PlanATripState) evt.getNewValue();


        }
    }
}
