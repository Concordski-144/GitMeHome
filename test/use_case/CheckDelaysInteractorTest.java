package use_case;

import org.junit.Test;
import org.junit.Assert.*;
import use_case.check_delays.*;

public class CheckDelaysInteractorTest {

    @Test
    public void testCheckDelaysInteractor() {
        CheckDelaysDataAccessInterface checkDelaysDAO = new CheckDelaysDataAccessInterface() {
            @Override
            public boolean checkDelaysByRoute(String id) {
                switch (id) {
                    case "CDTEST:1":
                        return true;
                    case "CDTEST:-1":
                        return false;
                    default:
                        throw new RuntimeException("Error: Invalid route ID");
                }
            }

            @Override
            public boolean checkDelaysByStation(String id) {
                switch (id) {
                    case "CDTEST:2":
                        return true;
                    case "CDTEST:-2":
                        return false;
                    default:
                        throw new RuntimeException("Error: Invalid route ID");
                }
            }

            @Override
            public String getRouteName() {
                return "Test Route";
            }

            @Override
            public String getStationName() {
                return "Test Station";
            }
        };

        CheckDelaysOutputBoundary checkDelaysPresenterDelayed = new CheckDelaysOutputBoundary() {
            @Override
            public void prepareSuccessView(CheckDelaysOutputData checkDelaysOutputData) {
                if (checkDelaysOutputData.getDelayStatus()) {
                    assert true;
                } else {
                    assert false;
                }
            }
            @Override
            public void prepareFailView(String error) {
                assert false;
            }
        };

        CheckDelaysOutputBoundary checkDelaysPresenterNotDelayed = new CheckDelaysOutputBoundary() {
            @Override
            public void prepareSuccessView(CheckDelaysOutputData checkDelaysOutputData) {
                if (checkDelaysOutputData.getDelayStatus()) {
                    assert false;
                } else {
                    assert true;
                }
            }
            @Override
            public void prepareFailView(String error) {
                assert false;
            }
        };

        CheckDelaysOutputBoundary checkDelaysPresenterError = new CheckDelaysOutputBoundary() {
            @Override
            public void prepareSuccessView(CheckDelaysOutputData checkDelaysOutputData) {
                if (checkDelaysOutputData.getDelayStatus()) {
                    assert false;
                } else {
                    assert false;
                }
            }
            @Override
            public void prepareFailView(String error) {
                assert true;
            }
        };

        CheckDelaysInputData inputDataRouteDelayed = new CheckDelaysInputData("CDTEST:1", "route");
        CheckDelaysInputData inputDataRouteNotDelayed = new CheckDelaysInputData("CDTEST:-1", "route");
        CheckDelaysInputData inputDataRouteError = new CheckDelaysInputData("CDTEST:0", "route");
        CheckDelaysInputData inputDataStationDelayed = new CheckDelaysInputData("CDTEST:2", "station");
        CheckDelaysInputData inputDataStationNotDelayed = new CheckDelaysInputData("CDTEST:-2", "station");
        CheckDelaysInputData inputDataStationError = new CheckDelaysInputData("CDTEST:0", "station");

        CheckDelaysInputBoundary interactorDelayed = new CheckDelaysInteractor(checkDelaysDAO, checkDelaysPresenterDelayed);
        CheckDelaysInputBoundary interactorNotDelayed = new CheckDelaysInteractor(checkDelaysDAO, checkDelaysPresenterNotDelayed);
        CheckDelaysInputBoundary interactorError = new CheckDelaysInteractor(checkDelaysDAO, checkDelaysPresenterError);

        interactorDelayed.execute(inputDataRouteDelayed);
        interactorNotDelayed.execute(inputDataRouteNotDelayed);
        interactorError.execute(inputDataRouteError);
        interactorDelayed.execute(inputDataStationDelayed);
        interactorNotDelayed.execute(inputDataStationNotDelayed);
        interactorError.execute(inputDataStationError);
    }
}
