package interface_adapter.get_details;

import java.util.ArrayList;

public class GetDetailsState {
    private String routeID = "";
    private String routeIDError = null;
    private boolean withDepartures = false;
    private ArrayList<String> details;
    public GetDetailsState(GetDetailsState copy) {
        routeID = copy.routeID;
        routeIDError = copy.routeIDError;
        withDepartures = copy.withDepartures;
        details = copy.details;
    }
    public GetDetailsState(){}
    public String DetailsToString(){
        String stringDetails = "";
        for (String s: details){
            stringDetails = stringDetails + s + "\n";
        }
        return stringDetails;
    }

    public ArrayList<String>GetDetails(){return details;}

    public String getRouteID() {
        return routeID;
    }

    public boolean isWithDepartures() {
        return withDepartures;
    }

    public String getRouteIDError() {
        return routeIDError;
    }

    public void setDetails(ArrayList<String> details) {
        this.details = details;
    }

    public void setRouteID(String routeID) {
        this.routeID = routeID;
    }

    public void setRouteIDError(String routeIDError) {
        this.routeIDError = routeIDError;
    }

    public void setWithDepartures(boolean withDepartures) {
        this.withDepartures = withDepartures;
    }
}
