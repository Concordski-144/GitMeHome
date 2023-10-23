package interface_adapter.clear_users;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ClearViewModel extends ViewModel {
    public final String CLEAR_SUCCESS = "Successfully cleared all users.";
    public String clearedUsers = "";
    private ClearState state;

    public ClearViewModel() {
        super("clear");
    }

    public void setState(ClearState state) {
        this.state = state;
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

    public ClearState getState() {
        return state;
    }

    public void setClearedUsers(String displayStr) {
        this.clearedUsers = displayStr;
    }
}
