package interface_adapter.longitude_latitude;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LonLatViewModel extends ViewModel {
    public static final String LON_LAT_BUTTON_LABEL = "Where are you";
    private LonLatState state = new LonLatState();
    public LonLatViewModel(String viewName) {
        super("get the longitude and latitude");
    }
    public void setState(LonLatState state) {
        this.state = state;
    }
    public LonLatState getState() {
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
