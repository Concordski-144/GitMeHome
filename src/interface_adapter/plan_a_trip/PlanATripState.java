package interface_adapter.plan_a_trip;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class PlanATripState {
    private String fromPlace = "";
    private String toPlace = "";
    private String placeError = null;
    private HashMap<String, Object> planMap  = null;


    public PlanATripState(PlanATripState copy) {
        fromPlace = copy.fromPlace;
        toPlace = copy.toPlace;
        placeError = copy.placeError;
        planMap = copy.planMap;
    }

    public PlanATripState() {
    }

    public String getFromPlace() {
        return fromPlace;
    }

    public String getToPlace() {
        return toPlace;
    }

    public String getPlaceError(){
        return placeError;
    }

    public HashMap<String, Object> getPlanMap() {
        return planMap;
    }

    public void setFromPlace(String from) {
        this.fromPlace = from;
    }

    public void setToPlace(String to) {
        this.toPlace = to;
    }

    public void setPlaceError(String placeError){
        this.placeError = placeError;
    }

    public void setPlanMap(HashMap<String, Object> planMap){
        this.planMap = planMap;
    }

    @Override
    public String toString(){
        String output = "Planned trip:" + '\n' + "Duration of trip: " + planMap.get("duration") + " seconds \n"
                + "From: (" + planMap.get("fromLat") + ", " + planMap.get("fromLon") + ") \n"
                + "To: (" + planMap.get("toLat") + ", " + planMap.get("toLon") + ") \n";
        for (HashMap leg : (ArrayList <HashMap>) planMap.get("legs")) {
            output += leg + "\n";
        }

        return output;
    }
}
