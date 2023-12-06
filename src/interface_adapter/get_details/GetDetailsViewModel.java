package interface_adapter.get_details;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import view.GetDetailsView;

import java.beans.PropertyChangeListener;

public class GetDetailsViewModel extends ViewModel{
    public static final String GET_DETAILS_BUTTON_LABEL = "Get Details";
    public static final String ROUTEID_LABEL = "Choose StationID";

    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    public static final String WITH_DEPARTURE_TIME_BUTTON_LABEL = "Show Departure Time";
    private GetDetailsState state = new GetDetailsState();

    public GetDetailsViewModel(String viewName) {
        super("get details");
    }

    public void setState(GetDetailsState state) {
        this.state = state;
    }
    public GetDetailsState getState(){
        return state;
    }
    public static final String TITLE_LABEL = "GET_DETAILS";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
