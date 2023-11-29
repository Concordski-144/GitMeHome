package use_case.plan_a_trip;

import entity.Route;

import java.util.HashMap;
import java.util.List;

public interface PlanATripDataAccessInterface {
    HashMap<String, Object> getPlanForTrip(String fromPlace, String toPlace);
}
