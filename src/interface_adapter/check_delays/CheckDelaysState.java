package interface_adapter.check_delays;

import java.util.ArrayList;

public class CheckDelaysState {
    private String stationID = "";
    private Integer time = null;
    private String stationIDError = "";
    private ArrayList<Integer> departureTime;

    public CheckDelaysState(CheckDelaysState copy) {
        stationID = copy.stationID;
        time = copy.time;
        stationIDError = copy.stationIDError;
        departureTime = copy.departureTime;
    }
    public CheckDelaysState(){}

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

    public String getType() {
        return "";
    }

    public String getStationID() {
        return stationID;
    }

    public String getStationIDError() {
        return stationIDError;
    }

    @Override
    public String toString(){
        return "CheckDelaysState{" +
                "stationID= " + stationID + '\'' +
                ", departure time= " + time + '\'' +
                "}";
    }
}
