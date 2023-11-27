package use_case.get_details;

public class GetDetailsInputData{
    final private String stationID;
    final private boolean withDepartureTime;
    public GetDetailsInputData(String StationID, boolean WithDepartureTime){
        this.stationID = StationID;
        this.withDepartureTime = WithDepartureTime;
    }

    String getStationID(){
        return stationID;
    }
    Boolean isWithDepartureTime(){return withDepartureTime;}
}
