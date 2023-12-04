package use_case.check_delays;
import entity.Delay;

import java.util.ArrayList;

public interface CheckDelaysDataAccessInterface {
    ArrayList<Delay> checkDelaysByRoute(String id);

    boolean checkDelaysByStation(String id);
}
