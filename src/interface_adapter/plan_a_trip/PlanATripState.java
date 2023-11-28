package interface_adapter.plan_a_trip;

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
}
