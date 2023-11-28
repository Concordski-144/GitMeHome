package use_case.plan_a_trip;

public interface PlanATripOutputBoundary {
    void prepareSuccessView(PlanATripOutputData user);

    void prepareFailView(String error);
}
