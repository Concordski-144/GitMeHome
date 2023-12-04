package use_case.check_delays;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckDelaysOutputData {
    private final HashMap<String, String> delaysByRoute;
    private boolean useCaseFailed;

    public CheckDelaysOutputData(HashMap<String,  String> delaysByRoute, boolean useCaseFailed) {
        this.delaysByRoute = delaysByRoute;
        this.useCaseFailed = useCaseFailed;
    }

    public HashMap<String,  String> getDelaysByRoute() {
        return delaysByRoute;
    }
}
