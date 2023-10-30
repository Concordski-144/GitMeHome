package entity;

import java.util.ArrayList;

public interface DelayFactory {
    Delay create(String name, String id, String delay_type, String delay_status, int start_time, int end_time_estimated, ArrayList<Route> affected_routes);
}
