package interface_adapter.plan_a_trip;

import use_case.plan_a_trip.PlanATripInputBoundary;
import use_case.plan_a_trip.PlanATripInputData;


public class PlanATripController {
    final PlanATripInputBoundary planATripInteractor;
    public PlanATripController(PlanATripInputBoundary planATripInteractor) {
        this.planATripInteractor = planATripInteractor;
    }

    public void execute(String fromPlace, String toPlace) {
        PlanATripInputData planATripInputData = new PlanATripInputData(fromPlace, toPlace);

        planATripInteractor.execute(planATripInputData);
    }
}
