package view;

import interface_adapter.get_details.GetDetailsController;
import interface_adapter.get_details.GetDetailsViewModel;
import interface_adapter.get_details.GetDetailsState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class GetDetailsView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "next departure";

    private final GetDetailsViewModel getDeTailsViewModel;
    private final JTextField stationIDInputField = new JTextField(15);

    private final GetDetailsController getDetailsController;

    private final JButton getDetails;
    private final JButton cancel;

    public GetDetailsView(GetDetailsController controller, GetDetailsViewModel viewModel) {

        this.getDetailsController = controller;
        this.getDeTailsViewModel = viewModel;
        getDeTailsViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(GetDetailsViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel stationIDInfo = new LabelTextPanel(
                new JLabel(GetDetailsViewModel.STATIONID_LABEL), stationIDInputField);


        JPanel buttons = new JPanel();
        getDetails = new JButton(GetDetailsViewModel.NEXT_DEPARTURE_BUTTON_LABEL);
        buttons.add(getDetails);
        cancel = new JButton(GetDetailsViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        getDetails.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(getDetails)) {
                            NextDepartureState currentState = getDetailsViewModel.getState();

                            getDetailsController.execute(
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
                        NextDepartureState currentState = getDetailsViewModel.getState();
                        String text = stationIDInputField.getText() + e.getKeyChar();
                        currentState.setStationID(text);
                        getDeTailsViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

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
