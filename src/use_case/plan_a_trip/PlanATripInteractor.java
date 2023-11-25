package use_case.plan_a_trip;

import use_case.next_departures.NextDepartureDataAccessInterface;
import use_case.next_departures.NextDepartureOutputBoundary;
import use_case.next_departures.NextDepartureOutputData;

import java.util.HashMap;

public class PlanATripInteractor implements PlanATripInputBoundary {

    final PlanATripDataAccessInterface planATripDataAccessObject;
    final PlanATripOutputBoundary planATripPresenter;

    public PlanATripInteractor(PlanATripDataAccessInterface planATripDataAccessObject, PlanATripOutputBoundary planATripPresenter) {
        this.planATripDataAccessObject = planATripDataAccessObject;
        this.planATripPresenter = planATripPresenter;
    }
    @Override
    public void execute(PlanATripInputData planATripInputData) {
        HashMap<String, Object> plan = planATripDataAccessObject.getPlanForTrip(planATripInputData.getFromPlace(), planATripInputData.getToPlace());
        PlanATripOutputData planATripOutputData = new PlanATripOutputData(plan, false);
        planATripPresenter.prepareSuccessView(planATripOutputData);
    }
}
