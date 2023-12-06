package view;

import app.ClosestStopsUseCaseFactory;
import app.Main;
import data_access.NextDeparturesDataAccessObject;
import entity.CommonStationFactory;
import entity.Route;
import entity.Station;
import entity.StationFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.closest_stops.ClosestStopsPresenter;
import interface_adapter.closest_stops.ClosestStopsViewModel;
import interface_adapter.next_departures.NextDeparturesViewModel;
import org.junit.Test;
import use_case.closest_stops.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ClosestStopsViewTest {
    private final ClosestStopsViewModel closestStopsViewModel = new ClosestStopsViewModel();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ClosestStopsDataAccessInterface closestStopsDataAccessObject = new ClosestStopsDataAccessInterface() {
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

    @Test
    public void testClosestStopsView() {
        ClosestStopsView closestStopsView = ClosestStopsUseCaseFactory.createClosestStopsView(
                viewManagerModel,
                closestStopsViewModel,
                closestStopsDataAccessObject,
                new NextDeparturesViewModel(),
                new NextDeparturesDataAccessObject());

        ClosestStopsOutputBoundary closestStopsPresenter = new ClosestStopsPresenter(closestStopsViewModel, viewManagerModel);
        ClosestStopsInputData closestStopsInputData = new ClosestStopsInputData(-79.39029050867504, 43.65925036795328, 5);
        ClosestStopsInputBoundary closestStopsInteractor = new ClosestStopsInteractor(closestStopsDataAccessObject, closestStopsPresenter);
        closestStopsInteractor.execute(closestStopsInputData);

        assert closestStopsView != null;
        assertEquals(closestStopsView.viewName, "closest stops");
        assertEquals(viewManagerModel.getActiveView(), "closest stops");
        assertEquals(closestStopsView.getStopLabel1().getText(), "<html>Queen's Park<br/>Distance: 1 m<br/>Transit mode: Subway<br/>Accessible: Yes</html>");
    }

}
