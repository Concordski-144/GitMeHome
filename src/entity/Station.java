package entity;

import java.util.ArrayList;

public interface Station {
    String getName();
    String getid();
    double[] getCoordinates();
    boolean getAccesibility();
    Integer[] getTransitModes();
    double getDistanceFromUser();
    Line[] getLines();
    ArrayList<Delay> getDelays();
    void setDelays(ArrayList<Delay> delays);
}
