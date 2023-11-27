package interface_adapter.get_details;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import view.GetDetailsView;

import java.beans.PropertyChangeListener;

public class GetDetailsViewModel {
    private GetDetailsState state = new GetDetailsState();
    public void setState(GetDetailsState state) {
        this.state = state;
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
