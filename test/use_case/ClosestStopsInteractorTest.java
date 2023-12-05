package use_case;

import entity.CommonStationFactory;
import entity.Route;
import entity.Station;
import entity.StationFactory;
import use_case.closest_stops.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClosestStopsInteractorTest {

    @org.junit.Test
    public void testClosestStopsInteractor() {
        ClosestStopsDataAccessInterface closestStopsDataAccessObject = new ClosestStopsDataAccessInterface() {
            @Override
            public List<Station> getClosestStops(double lon, double lat, int num) {
                ArrayList<Station> stations = new ArrayList<Station>();
                StationFactory stationFactory = new CommonStationFactory();
                Route[] routes = {};
                Station station1 = stationFactory.create(
                        "Queen's Park",
                        "TTC1ON:1079",
                        -79.39029050867504,
                        43.65925036795328,
                        true,
                        1,
                        1,
                        routes);
                stations.add(station1);
                Station station2 = stationFactory.create(
                        "University Ave / College St / Queen's Park Sta",
                        "TTC:139965",
                        -79.39048835914885,
                        43.65959210968077,
                        true,
                        3,
                        42,
                        routes);
                stations.add(station2);
                Station station3 = stationFactory.create(
                        "University Ave / College St / Queen's Park Sta",
                        "TTC:138993",
                        -79.39017359703142,
                        43.65971801452773,
                        true,
                        3,
                        53,
                        routes);
                stations.add(station3);
                Station station4 = stationFactory.create(
                        "College St / University Ave / Queen's Park Sta",
                        "TTC:126449",
                        -79.3907761416562,
                        43.65970002812103,
                        true,
                        0,
                        64,
                        routes);
                stations.add(station4);
                Station station5 = stationFactory.create(
                        "College St / Queen's Park",
                        "TTC:126521",
                        -79.390137624218,
                        43.65999680383174,
                        true,
                        0,
                        84,
                        routes);
                stations.add(station5);
                return stations;
            }
        };

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
        ClosestStopsInputBoundary interactor = new ClosestStopsInteractor(closestStopsDataAccessObject, closestStopsPresenter);
        interactor.execute(inputData);

    }
}
