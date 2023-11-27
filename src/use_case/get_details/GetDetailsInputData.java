package use_case.get_details;

public class GetDetailsInputData{
    final private String stationID;
    public GetDetailsInputData(String StationID){
        this.stationID = StationID;
    }

    String getStationID(){
        return stationID;
    }
}
