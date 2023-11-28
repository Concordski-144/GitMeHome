package interface_adapter.next_departures;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class NextDeparturesState {
    private String stationID = "";
    private Integer time = null;
    private String stationIDError = "";
    private HashMap<String, ArrayList<LocalDateTime>> departureTime;

    public NextDeparturesState (NextDeparturesState copy) {
        stationID = copy.stationID;
        time = copy.time;
        stationIDError = copy.stationIDError;
        departureTime = copy.departureTime;
    }
    public NextDeparturesState(){}

    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public void setDepartureTime(HashMap<String, ArrayList<LocalDateTime>> departureTime) {
        this.departureTime = departureTime;
    }

    public void setStationIDError(String stationIDError) {
        this.stationIDError = stationIDError;
    }

    public HashMap<String, ArrayList<LocalDateTime>> getDepartureTime() {
        return departureTime;
    }

    public Integer getTime() {
        return time;
    }

    public String getStationID() {
        return stationID;
    }

    public String getStationIDError() {
        return stationIDError;
    }

    @Override
    public String toString(){
        return "NextDeparturesState{" +
                "stationID= " + stationID + '\'' +
                ", departure time= " + time + '\'' +
                "}";
    }
}
