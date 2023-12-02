package interface_adapter.get_details;

import use_case.get_details.GetDetailsInputBoundary;
import use_case.get_details.GetDetailsInputData;
import use_case.get_details.GetDetailsInteractor;
import use_case.next_departures.NextDeparturesInputData;

public class GetDetailsController {
    final GetDetailsInputBoundary getDetailsInteractor;

    public GetDetailsController(GetDetailsInputBoundary getDetailsInteractor){
        this.getDetailsInteractor = getDetailsInteractor;
    }
    public void execute(String stationID, boolean withDepartureTime) {
        GetDetailsInputData getDetailsInputData = new GetDetailsInputData(
                stationID, withDepartureTime);

        getDetailsInteractor.execute(getDetailsInputData);
    }
}
