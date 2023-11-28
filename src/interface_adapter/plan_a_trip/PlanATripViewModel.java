package interface_adapter.plan_a_trip;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlanATripViewModel extends ViewModel {
    public static final String PLAN_A_TRIP_BUTTON_LABEL = "Plan your trip";
    public static final String TITLE_LABEL = "Plan A Trip View";
    public static final String FROM_PLACE_LABEL = "Choose starting location (Longitude and Latitude)";
    public static final String TO_PLACE_LABEL = "Choose destination (Longitude and Latitude)";

    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private PlanATripState state = new PlanATripState();

    public PlanATripViewModel() {
        super("get planned trip");
    }

    public void setState(PlanATripState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public PlanATripState getState() {
        return state;
    }
}
