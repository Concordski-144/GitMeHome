package use_case.next_departures;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class NextDeparturesInputData {

    final private String id;
    final private int time;

    public NextDeparturesInputData(String id, LocalDateTime time) {
        this.id = id;
        ZoneId zoneId = ZoneId.systemDefault();
        this.time = (int) time.atZone(zoneId).toEpochSecond();
    }

    String getId() {
        return id;
    }

    int getTime() {
        return time;
    }
}
