package entity;

import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

class CommonDelay implements Delay {

    private final String name;
    private final String id;
    private final String delay_type;
    private String delay_status;
    private final int start_time;
    private int end_time_estimated;
    private final Line[] affected_lines;

    CommonDelay(String name, String id, String delay_type, String delay_status, int start_time, int end_time_estimated, Line[] affected_lines) {
        this.name = name;
        this.id = id;
        this.delay_type = delay_type;
        this.delay_status = delay_status;
        this.start_time = start_time;
        this.end_time_estimated = end_time_estimated;
        this.affected_lines = affected_lines;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getid() {
        return id;
    }

    @Override
    public Line[] getAffectedLines() {
        return affected_lines;
    }

    @Override
    public String getDelayType() {
        return delay_type;
    }

    @Override
    public String getDelayStatus() {
        return delay_status;
    }

    @Override
    public int getStartTime() {
        return start_time;
    }

    @Override
    public int getEndTimeEstimated() {
        return end_time_estimated;
    }

    @Override
    public void setStatus(String status) {
        this.delay_status = status;
    }

    @Override
    public void setEndTimeEstimated(int end_time_estimated) {
        this.end_time_estimated = end_time_estimated;
    }
}
