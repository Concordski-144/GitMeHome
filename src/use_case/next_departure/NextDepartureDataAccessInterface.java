package use_case.next_departure;

import java.util.HashMap;

public interface NextDepartureDataAccessInterface {
    HashMap<String, Integer[]> getNextDeparturesByRoute(String id);
}
