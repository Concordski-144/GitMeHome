package use_case.check_delays;
import entity.Delay;

import java.util.ArrayList;

public interface CheckDelaysDataAccessInterface {
    boolean checkDelaysByRoute(String id);

    boolean checkDelaysByStation(String id);

    String getRouteName();

    String getStationName();
}
