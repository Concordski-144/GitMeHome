package entity;

import java.util.ArrayList;

class CommonDelay implements Delay {

    private final String name;
    private final String id;
    private final String delay_type;
    private String delay_status;
    private final int start_time;
    private int end_time_estimated;
    private final ArrayList<Route> affected_routes;

    CommonDelay(String name, String id, String delay_type, String delay_status, int start_time, int end_time_estimated, ArrayList<Route> affected_routes) {
        this.name = name;
        this.id = id;
        this.delay_type = delay_type;
        this.delay_status = delay_status;
        this.start_time = start_time;
        this.end_time_estimated = end_time_estimated;
        this.affected_routes = affected_routes;
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
    public ArrayList<Route> getAffectedLines() {
        return affected_routes;
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
