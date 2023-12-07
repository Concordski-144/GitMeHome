package entity;

import app.Main;

import javax.swing.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class EntityTest {


    @org.junit.Test
    public void testCommonStation() {
        Route[] routes = {};
        CommonStationFactory stationFactory = new CommonStationFactory();
        CommonStation station = (CommonStation) stationFactory.create("Station", "TTC:138961", -79.3999, 43.6683,
                false, 0, 12, routes);
        station.setDelays(new ArrayList<>());

        assertEquals(station.getCoordinates()[0], -79.3999, 0.05);
        assertEquals(station.getCoordinates()[1], 43.6683, 0.05);
        assertEquals(station.getLines(), routes);
        assertEquals(station.getDelays(), new ArrayList<Delay>());
    }


    @org.junit.Test
    public void testCommonDelay() {
        ArrayList<Route> routes = new ArrayList<>();
        CommonDelayFactory delayFactory = new CommonDelayFactory();
        CommonDelay delay = (CommonDelay) delayFactory.create("Station", "TTC:138961", "None", "None",
                123123213, 123123253, routes);
        delay.setEndTimeEstimated(123123243);
        delay.setStatus("Bad");

        assertEquals(delay.getName(), "Station");
        assertEquals(delay.getid(), "TTC:138961");
        assertEquals(delay.getAffectedLines(), routes);
        assertEquals(delay.getDelayType(), "None");
        assertEquals(delay.getDelayStatus(), "Bad");
        assertEquals(delay.getStartTime(), 123123213);
        assertEquals(delay.getEndTimeEstimated(), 123123243);
    }


    @org.junit.Test
    public void testSubwayRoute() {
        Station[] stations = {};
        SubwayRouteFactory subwayRouteFactory = new SubwayRouteFactory();
        SubwayRoute subwayRoute = (SubwayRoute) subwayRouteFactory.create("Station", "TTC:138961", stations);
        subwayRoute.setDepartureTimes(new ArrayList<>());
        subwayRoute.setDelays(new ArrayList<>());

        assertEquals(subwayRoute.getName(), "Station");
        assertEquals(subwayRoute.getid(), "TTC:138961");
        assertEquals(subwayRoute.getType(), "subway");
        assertEquals(subwayRoute.getRoute(), stations);
        assertEquals(subwayRoute.getDepartureTimes(), new ArrayList<LocalDateTime>());
        assertEquals(subwayRoute.getDelays(), new ArrayList<Delay>());
    }
}
