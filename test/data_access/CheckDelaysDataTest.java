package data_access;

import org.junit.Test;
import use_case.check_delays.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CheckDelaysDataTest {
    @Test
    public void testCheckDelaysInteractor() {
        CheckDelaysDataAccessObject dataAccessObject = new CheckDelaysDataAccessObject();

        CheckDelaysOutputBoundary successPresenterStation = new CheckDelaysOutputBoundary() {
            @Override
            public void prepareSuccessView(CheckDelaysOutputData user) {
                assertEquals(user.getName(), "the Station");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };


        CheckDelaysOutputBoundary successPresenterRoute = new CheckDelaysOutputBoundary() {
            @Override
            public void prepareSuccessView(CheckDelaysOutputData user) {
                assertEquals(user.getDelayStatus(), false);
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        CheckDelaysInputData inputDataStation = new CheckDelaysInputData("TTC:138961", "station");
        CheckDelaysInputBoundary interactorStation = new CheckDelaysInteractor(
                dataAccessObject, successPresenterStation);
        interactorStation.execute(inputDataStation);

        CheckDelaysInputData inputDataRoute = new CheckDelaysInputData("TTC1ON:112634", "route");
        CheckDelaysInputBoundary interactorRoute = new CheckDelaysInteractor(
                dataAccessObject, successPresenterRoute);
            interactorRoute.execute(inputDataRoute);
    }
}
