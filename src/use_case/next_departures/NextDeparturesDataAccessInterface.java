package use_case.next_departures;

import entity.Route;
import java.util.List;

public interface NextDeparturesDataAccessInterface {
    List<Route> getNextDeparturesByRoute(String id, int time);
}
