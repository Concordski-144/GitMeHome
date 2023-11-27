package use_case.next_departures;
import entity.Route;

import java.util.List;

public interface NextDepartureDataAccessInterface {
    List<Route> getNextDeparturesByRoute(String id, int time);
}
