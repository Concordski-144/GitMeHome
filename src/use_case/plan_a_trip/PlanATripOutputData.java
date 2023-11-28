package use_case.plan_a_trip;

import java.util.ArrayList;
import java.util.HashMap;

public class PlanATripOutputData {
    private final HashMap<String, Object> planMap;
    private boolean useCaseFailed;

    public PlanATripOutputData(HashMap<String,  Object> planMap, boolean useCaseFailed) {
        this.planMap = planMap;
        this.useCaseFailed = useCaseFailed;
    }

    public HashMap<String,  Object> getPlanMap() {
        return planMap;
    }
}
