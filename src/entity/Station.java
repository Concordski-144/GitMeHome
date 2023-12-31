package entity;

import java.util.ArrayList;

public interface Station {
    String getName();
    String getid();
    double[] getCoordinates();
    boolean getAccessibility();
    Integer getTransitMode();
    Integer getDistanceFromUser();
    Route[] getLines();
    ArrayList<Delay> getDelays();
    void setDelays(ArrayList<Delay> delays);
}
