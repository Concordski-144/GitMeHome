package interface_adapter.closest_stops;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ClosestStopsViewModel extends ViewModel {

    private ClosestStopsState state = new ClosestStopsState();

    public ClosestStopsViewModel() {
        super("closest stops");
    }

    public void setState(ClosestStopsState state) {
        this.state = state;
    }

    public ClosestStopsState getState() {
        return state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
