package data_access;

import use_case.plan_a_trip.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PlanATripDataTest {

    @org.junit.Test
    public void testPlanATripData() {
        PlanATripDataAccessObject dataAccessObject = new PlanATripDataAccessObject();

        PlanATripOutputBoundary successPresenter = new PlanATripOutputBoundary() {
            @Override
            public void prepareSuccessView(PlanATripOutputData user) {
                assertEquals(43.66829753052834, (Double) user.getPlanMap().get("fromLat"), 0.05);
                assertEquals(-79.3998952498581, (Double) user.getPlanMap().get("fromLon"), 0.05);
                assertEquals("", user.getPlanMap().get("fromName"));
                assertEquals(43.66199329497653, (Double) user.getPlanMap().get("toLat"), 0.05);
                assertEquals(-79.3998952498581, (Double) user.getPlanMap().get("toLon"), 0.05);
                assertEquals("", user.getPlanMap().get("toName"));
                assertEquals(773, user.getPlanMap().get("duration"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        PlanATripInputData inputData = new PlanATripInputData("43.6683, -79.3999", "43.6620, -79.3999");
        PlanATripInputBoundary interactor = new PlanATripInteractor(
                dataAccessObject, successPresenter);
        interactor.execute(inputData);
    }
}
