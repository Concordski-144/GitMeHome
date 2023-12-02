package interface_adapter.next_departures;

import use_case.next_departures.NextDeparturesInputBoundary;
import use_case.next_departures.NextDeparturesInputData;
import use_case.next_departures.NextDeparturesOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NextDeparturesController {
    final NextDeparturesInputBoundary nextDeparturesInteractor;

    public NextDeparturesController (NextDeparturesInputBoundary nextDeparturesInteractor) {
        this.nextDeparturesInteractor = nextDeparturesInteractor;
    }

    public void execute(String stationID, String time, boolean emptyCheck) {
        NextDeparturesInputData nextDeparturesInputData;
        if (!emptyCheck) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            nextDeparturesInputData = new NextDeparturesInputData(stationID, LocalDateTime.parse(time, formatter));
        }
        else {
            nextDeparturesInputData = new NextDeparturesInputData(stationID, LocalDateTime.now());
        }
        nextDeparturesInteractor.execute(nextDeparturesInputData);
    }
}
