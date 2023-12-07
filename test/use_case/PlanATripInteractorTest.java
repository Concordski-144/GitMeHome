package use_case;

import use_case.plan_a_trip.*;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PlanATripInteractorTest {

    @org.junit.Test
    public void testPlanATripInteractor() {
        PlanATripDataAccessInterface userRepository = new PlanATripDataAccessInterface() {
            @Override
            public HashMap<String, Object> getPlanForTrip(String fromPlace, String toPlace) {
                HashMap<String, Object> output = new HashMap<>();
                output.put("fromLat", 43.66829753052834);
                output.put("fromLon", -79.3998952498581);
                output.put("fromName", "");
                output.put("toLat", 43.66199329497653);
                output.put("toLon", -79.3998952498581);
                output.put("toName", "");
                output.put("duration", 773);
                output.put("transitTime", 240);
                output.put("walkTime", 533);
                HashMap<String, Object> temp = new HashMap<>();
                temp.put("duration", 377);
                temp.put("mode", "WALK");
                temp.put("legsFromLat", 43.66824357130821);
                temp.put("legsFromLon", -79.39987726345139);
                temp.put("legsFromName", "");
                temp.put("legsToLat", 43.66718237331233);
                temp.put("legsToLon", -79.40360944284369);
                temp.put("legsToName", "");
                return output;
            }
        };

        PlanATripOutputBoundary successPresenter = new PlanATripOutputBoundary() {
            @Override
            public void prepareSuccessView(PlanATripOutputData user) {
                assertEquals(773, user.getPlanMap().get("duration"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        PlanATripInputData inputData = new PlanATripInputData("43.6683, -79.3999", "43.6620, -79.3999");
        PlanATripInputBoundary interactor = new PlanATripInteractor(
                userRepository, successPresenter);
        interactor.execute(inputData);
    }
}
