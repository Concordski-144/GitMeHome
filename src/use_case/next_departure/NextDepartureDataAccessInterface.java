package use_case.next_departure;
import entity.Route;

import java.util.List;

public interface NextDepartureDataAccessInterface {
    List<Route> getNextDeparturesByRoute(String id);
}
