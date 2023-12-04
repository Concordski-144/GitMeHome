package interface_adapter.check_delays;

import interface_adapter.ViewModel;
import interface_adapter.check_delays.CheckDelaysState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CheckDelaysViewModel extends ViewModel {

    public static final String CHECK_DELAYS_BUTTON_LABEL = "Check Delays";
    public static final String TITLE_LABEL = "Check Delay View";
    public static final String STATIONID_LABEL = "Choose StationID";

    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private CheckDelaysState state = new CheckDelaysState();

    public CheckDelaysViewModel() {
        super("get delays");
    }

    public void setState(CheckDelaysState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Presenter will call to let the ViewModel know to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CheckDelaysState getState() {
        return state;
    }
}
