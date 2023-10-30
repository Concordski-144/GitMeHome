package entity;

import java.util.ArrayList;

public interface Line {
    String getName();
    String getid();
    String getType();
    double[] getCoordinates();
    void setCoordinates(double[] coordinates);
    Station[] getRoute();
    Station getDestination();
    ArrayList<Integer> getDepartureTimes();
    void setDepartureTimes(ArrayList<Integer> departureTimes);
    ArrayList<Delay> getDelays();
    void setDelays(ArrayList<Delay> delays);
}
