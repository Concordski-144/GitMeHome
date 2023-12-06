package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface Route {
    String getName();
    String getid();
    String getType();
    Station[] getRoute();
    Station getDestination();
    ArrayList<LocalDateTime> getDepartureTimes();
    void setDepartureTimes(ArrayList<LocalDateTime> departureTimes);
    ArrayList<Delay> getDelays();
    void setDelays(ArrayList<Delay> delays);
}
