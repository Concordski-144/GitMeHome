package use_case.check_delays;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckDelaysOutputData {
    private final boolean status;
    private final String name;
    private boolean useCaseFailed;

    public CheckDelaysOutputData(boolean status, String name, boolean useCaseFailed) {
        this.status = status;
        this.name = name;
        this.useCaseFailed = useCaseFailed;
    }

    public boolean getDelayStatus() {
        return status;
    }

    public String getName() {
        return name;
    }
}
