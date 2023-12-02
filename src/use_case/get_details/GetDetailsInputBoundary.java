package use_case.get_details;

import use_case.next_departures.NextDeparturesInputData;

public interface GetDetailsInputBoundary {
    void execute(GetDetailsInputData getDetailsInputData);

}
