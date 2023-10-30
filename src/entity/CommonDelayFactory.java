package entity;

import java.time.LocalDateTime;

public class CommonDelayFactory implements DelayFactory {
    @Override
    public Delay create(String name, String id, String delay_type, String delay_status, int start_time, int end_time_estimated, Line[] affected_lines){
        return new CommonDelay(name, id, delay_type, delay_status, start_time, end_time_estimated, affected_lines);
    }
}
