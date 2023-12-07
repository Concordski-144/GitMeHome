package view;

import interface_adapter.ViewManagerModel;
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
    public final String viewName = "get details";
    private final ViewManagerModel viewManagerModel;

    private final GetDetailsViewModel getDetailsViewModel;
    private final JTextField routeIDInputField = new JTextField(15);

    private final GetDetailsController getDetailsController;

    private final JButton getDetails;
    private final JButton cancel;
    private final JButton departuretime;
    public boolean withDepartureTime = false;

    public GetDetailsView(GetDetailsController controller, GetDetailsViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.getDetailsController = controller;
        this.getDetailsViewModel = viewModel;
        getDetailsViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(GetDetailsViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel routeIDInfo = new LabelTextPanel(
                new JLabel(GetDetailsViewModel.ROUTEID_LABEL), routeIDInputField);


        JPanel buttons = new JPanel();
        getDetails = new JButton(GetDetailsViewModel.GET_DETAILS_BUTTON_LABEL);
        buttons.add(getDetails);
        cancel = new JButton(GetDetailsViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);
        departuretime = new JButton(GetDetailsViewModel.GET_DETAILS_BUTTON_LABEL);
        buttons.add(departuretime);

        getDetails.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(getDetails)) {
                            GetDetailsState currentState = getDetailsViewModel.getState();

                            getDetailsController.execute(
                                    currentState.getRouteID(), currentState.isWithDepartures()
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
        routeIDInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        GetDetailsState currentState = getDetailsViewModel.getState();
                        String text = routeIDInputField.getText() + e.getKeyChar();
                        currentState.setRouteID(text);
                        getDetailsViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        departuretime.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(departuretime)) {
                            GetDetailsState currentState = getDetailsViewModel.getState();
                            if (withDepartureTime){withDepartureTime = false;}
                            else{withDepartureTime = true;}
                            currentState.setWithDepartures(withDepartureTime);
                        }

                    }
                }

        );
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(cancel)) {
            JOptionPane.showConfirmDialog(this, "Cancel not implemented yet.");}
        else if (evt.getSource().equals(departuretime)){
            if (withDepartureTime) {
                JOptionPane.showMessageDialog(this, "With Departure Time");
            }
            else {
                JOptionPane.showMessageDialog(this, "Without Departure Time");
            }
        }
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Objects.equals(evt.getPropertyName(), "state")) {
            GetDetailsState state = (GetDetailsState) evt.getNewValue();
            if (state.getRouteIDError() != null) {
                JOptionPane.showMessageDialog(this, state.getRouteIDError());
            }
            else{
                GetDetailsState state1 = (GetDetailsState) evt.getNewValue();
                JOptionPane.showMessageDialog(this, state1.DetailsToString());
            }

        }
    }

}
