package use_case.check_delays;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckDelaysOutputData {
    private final boolean status;
    private boolean useCaseFailed;

    public CheckDelaysOutputData(boolean status, boolean useCaseFailed) {
        this.status = status;
        this.useCaseFailed = useCaseFailed;
    }

    public boolean getDelayStatus() {
        return status;
    }
}
