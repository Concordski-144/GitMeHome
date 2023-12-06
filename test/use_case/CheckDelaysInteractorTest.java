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
                        assert false;
                        return false;
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
                        assert false;
                        return false;
                }
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

        CheckDelaysInputData inputDataRouteDelayed = new CheckDelaysInputData("CDTEST:1", "route");
        CheckDelaysInputData inputDataRouteNotDelayed = new CheckDelaysInputData("CDTEST:-1", "route");
        CheckDelaysInputData inputDataStationDelayed = new CheckDelaysInputData("CDTEST:2", "station");
        CheckDelaysInputData inputDataStationNotDelayed = new CheckDelaysInputData("CDTEST:-2", "station");

        CheckDelaysInputBoundary interactorRouteDelayed = new CheckDelaysInteractor(checkDelaysDAO, checkDelaysPresenterDelayed);
        CheckDelaysInputBoundary interactorRouteNotDelayed = new CheckDelaysInteractor(checkDelaysDAO, checkDelaysPresenterNotDelayed);
        CheckDelaysInputBoundary interactorStationDelayed = new CheckDelaysInteractor(checkDelaysDAO, checkDelaysPresenterDelayed);
        CheckDelaysInputBoundary interactorStationNotDelayed = new CheckDelaysInteractor(checkDelaysDAO, checkDelaysPresenterNotDelayed);

        interactorRouteDelayed.execute(inputDataRouteDelayed);
        interactorRouteNotDelayed.execute(inputDataRouteNotDelayed);
        interactorStationDelayed.execute(inputDataStationDelayed);
        interactorStationNotDelayed.execute(inputDataStationNotDelayed);
    }
}
