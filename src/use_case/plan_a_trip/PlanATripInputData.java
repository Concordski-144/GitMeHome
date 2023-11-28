package use_case.plan_a_trip;

public class PlanATripInputData {
    final private String fromPlace;
    final private String toPlace;

    public PlanATripInputData(String fromPlace, String toPlace) {
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
    }

    String getFromPlace() {
        return fromPlace;
    }

    String getToPlace() {
        return toPlace;
    }
}
