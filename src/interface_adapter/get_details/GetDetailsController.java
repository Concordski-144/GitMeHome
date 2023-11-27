package interface_adapter.get_details;

import use_case.get_details.GetDetailsInputData;
import use_case.next_departures.NextDeparturesInputData;

public class GetDetailsController {
    public void execute(String stationID) {
        GetDetailsInputData getDetailsInputData = new GetDetailsInputData(
                stationID);

        getDetailsInteractor.execute(getDetailsInputData);
    }
}
