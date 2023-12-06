package use_case;

import entity.Route;
import entity.Station;
import entity.SubwayRouteFactory;
import use_case.next_departures.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class NextDeparturesInteractorTest {

    @org.junit.Test
    public void testNextDeparturesInteractor() {
        NextDeparturesDataAccessInterface userRepository = new NextDeparturesDataAccessInterface() {

            @Override
            public java.util.List<Route> getNextDeparturesByRoute(String id, int time) {
                ArrayList<Route> routes = new ArrayList<Route>();
                SubwayRouteFactory subwayRouteFactory = new SubwayRouteFactory();
                Station[] stations = {};
                ArrayList<LocalDateTime> departures = new ArrayList<LocalDateTime>();
                departures.add(LocalDateTime.ofEpochSecond(1701713160, 0, OffsetDateTime.now().getOffset()));
                departures.add(LocalDateTime.ofEpochSecond(1701714960, 0, OffsetDateTime.now().getOffset()));
                departures.add(LocalDateTime.ofEpochSecond(1701716760, 0, OffsetDateTime.now().getOffset()));
                Route route = subwayRouteFactory.create("13 Avenue Road", id, stations);
                route.setDepartureTimes(departures);
                routes.add(route);

                return routes;
            }
        };

        NextDeparturesOutputBoundary successPresenter = new NextDeparturesOutputBoundary() {
            @Override
            public void prepareSuccessView(NextDeparturesOutputData user) {
                assertEquals(LocalDateTime.ofEpochSecond(1701713160, 0, OffsetDateTime.now().getOffset()),
                        user.getDeparturesByRoute().get("TTC:138961").get(0));
                assertEquals(LocalDateTime.ofEpochSecond(1701714960, 0, OffsetDateTime.now().getOffset()),
                        user.getDeparturesByRoute().get("TTC:138961").get(1));
                assertEquals(LocalDateTime.ofEpochSecond(1701716760, 0, OffsetDateTime.now().getOffset()),
                        user.getDeparturesByRoute().get("TTC:138961").get(2));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        NextDeparturesInputData inputData = new NextDeparturesInputData("TTC:138961", LocalDateTime.ofEpochSecond(1701711649, 0, OffsetDateTime.now().getOffset()));
        NextDeparturesInputBoundary interactor = new NextDeparturesInteractor(
                userRepository, successPresenter);
        interactor.execute(inputData);
    }
}
