package interface_adapter.get_details;

import interface_adapter.next_departures.NextDepartureState;

public class GetDetailsState {
    private String routeID = "";
    private String routeIDError = null;
    private boolean withDepartures = false;
    public GetDetailsState(GetDetailsState copy) {
        routeID = copy.routeID;
        routeIDError = copy.routeIDError;
        withDepartures = copy.withDepartures;
    }

    public GetDetailsState(){}

    public String getRouteID() {
        return routeID;
    }

    public boolean isWithDepartures() {
        return withDepartures;
    }

    public String getRouteIDError() {
        return routeIDError;
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
