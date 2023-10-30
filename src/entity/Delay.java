package entity;

import java.util.ArrayList;

public interface Delay {
    String getName();
    String getid();
    ArrayList<Route> getAffectedLines();
    String getDelayType();
    String getDelayStatus();
    int getStartTime();
    int getEndTimeEstimated();
    void setStatus(String status);
    void setEndTimeEstimated(int end_time_estimated);
}
