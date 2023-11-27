package use_case.closest_stops;

import java.util.List;
import entity.Station;

public interface ClosestStopsDataAccessInterface {
    List<Station> getClosestStops(double lon, double lat, int num);
}
