package interface_adapter.next_departures;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NextDeparturesViewModel extends ViewModel {

    public static final String NEXT_DEPARTURE_BUTTON_LABEL = "Next Departure";
    public static final String TITLE_LABEL = "Next Departure View";
    public static final String STATIONID_LABEL = "Choose StationID";
    public static final String TIME = "Choose time";

    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private NextDeparturesState state = new NextDeparturesState();

    public NextDeparturesViewModel() {
        super("get departure time");
    }

    public void setState(NextDeparturesState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public NextDeparturesState getState() {
        return state;
    }
}
