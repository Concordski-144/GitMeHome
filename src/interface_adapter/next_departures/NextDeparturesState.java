package interface_adapter.next_departures;

import java.util.ArrayList;

public class NextDeparturesState {
    private String stationID = "";
    private Integer time = null;
    private String stationIDError = "";
    private ArrayList<Integer> departureTime;

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

    public void setDepartureTime(ArrayList<Integer> departureTime) {
        this.departureTime = departureTime;
    }

    public void setStationIDError(String stationIDError) {
        this.stationIDError = stationIDError;
    }

    public ArrayList<Integer> getDepartureTime() {
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
