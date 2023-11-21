package interface_adapter.next_departures;

import java.util.ArrayList;
import java.util.HashMap;

public class NextDepartureState {
    private String stationID = "";
    private Integer time = null;
    private String stationIDError = null;
    private ArrayList<Integer> departureTime;


    public NextDepartureState(NextDepartureState copy) {
        stationID = copy.stationID;
        time = copy.time;
        stationIDError = copy.stationIDError;
        departureTime = copy.departureTime;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public NextDepartureState() {
    }

    public String getStationID() {
        return stationID;
    }

    public int getTime() {
        return time;
    }

    public String getStationIDError(){return stationIDError;}

    public ArrayList<Integer> getDepartureTime(){return departureTime;}

    public void setStationID(String username) {
        this.stationID = stationID;
    }

    public void setTime(int departureTime) {
        this.time = departureTime;
    }

    public void setStationIDError(String stationIDError){this.stationIDError = stationIDError;}

    public void setDepartureTime(ArrayList<Integer> departureTime){this.departureTime = departureTime;}

    @Override
    public String toString() {
        return "NextDepartureState{" +
                "stationID='" + stationID + '\'' +
                ", departure time='" + time + '\'' +
                '}';
    }
}
