package use_case.check_delays;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckDelaysOutputData {
    private final HashMap<String, ArrayList<Integer>> delaysByRoute;
    private boolean useCaseFailed;

    public CheckDelaysOutputData(HashMap<String,  ArrayList<Integer>> delaysByRoute, boolean useCaseFailed) {
        this.delaysByRoute = delaysByRoute;
        this.useCaseFailed = useCaseFailed;
    }

    public HashMap<String,  ArrayList<Integer>> getDelaysByRoute() {
        return delaysByRoute;
    }
}
