package data_access;

import use_case.closest_stops.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ClosestStopsDataTest {

    @org.junit.Test
    public void testClosestStopsInteractor() {
        ClosestStopsDataAccessObject dataAccessObject = new ClosestStopsDataAccessObject();

        ClosestStopsOutputBoundary closestStopsPresenter = new ClosestStopsOutputBoundary() {
            @Override
            public void prepareSuccessView(ClosestStopsOutputData response) {
                List<Object> stop = response.getStops().get("TTC1ON:1079");
                assertEquals(stop.get(0), "Queen's Park");
                assertEquals(stop.get(1), 1);
                assertEquals(stop.get(2), 1);
                assertEquals(stop.get(3), true);
            }

            @Override
            public void prepareFailView(String error) {
                fail(error);
            }
        };

        ClosestStopsInputData inputData = new ClosestStopsInputData(-79.39029050867504, 43.65925036795328, 5);
        ClosestStopsInputBoundary interactor = new ClosestStopsInteractor(dataAccessObject, closestStopsPresenter);
        interactor.execute(inputData);

    }
}
