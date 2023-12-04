package interface_adapter.check_delays;

import java.util.ArrayList;

public class CheckDelaysState {
    private String stationID = "";
    private String queryType = "";
    private String stationIDError = "";
    private boolean delayed;

    public CheckDelaysState(CheckDelaysState copy) {
        stationID = copy.stationID;
        queryType = copy.queryType;
        stationIDError = copy.stationIDError;
        delayed = copy.delayed;
    }
    public CheckDelaysState(){}

    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public void setDelayStatus(boolean delayed) {
        this.delayed = delayed;
    }

    public void setStationIDError(String stationIDError) {
        this.stationIDError = stationIDError;
    }

    public boolean getDelayStatus() {
        return delayed;
    }

    public String getType() {
        return this.queryType;
    }

    public String getStationID() {
        return stationID;
    }

    public String getStationIDError() {
        return stationIDError;
    }

    @Override
    public String toString(){
        if (delayed) {
            return "Lines at " + stationID.substring(0, stationID.length() - 1) + " is delayed.";
        } else {
            return "There is no delay at " + stationID.substring(0, stationID.length() - 1) + ".";
        }
    }
}
