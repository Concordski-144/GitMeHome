package use_case.next_departures;

public class NextDeparturesInputData {

    final private String id;
    final private int time;

    public NextDeparturesInputData(String id, int time) {
        this.id = id;
        this.time = time;
    }

    String getId() {
        return id;
    }

    int getTime() {
        return time;
    }
}
