package use_case.get_details;

public class GetDetailsInputData{
    final private String routeID;
    final private boolean withDepartureTime;
    public GetDetailsInputData(String StationID, boolean WithDepartureTime){
        this.routeID = StationID;
        this.withDepartureTime = WithDepartureTime;
    }

    String getRouteID(){
        return routeID;
    }
    Boolean isWithDepartureTime(){return withDepartureTime;}
}
