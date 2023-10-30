package entity;

import java.util.ArrayList;

public class CommonDelayFactory implements DelayFactory {
    @Override
    public Delay create(String name, String id, String delay_type, String delay_status, int start_time, int end_time_estimated, ArrayList<Route> affected_routes){
        return new CommonDelay(name, id, delay_type, delay_status, start_time, end_time_estimated, affected_routes);
    }
}
