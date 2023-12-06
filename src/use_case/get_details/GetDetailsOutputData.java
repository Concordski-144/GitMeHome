package use_case.get_details;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class GetDetailsOutputData {
    private final ArrayList<String> details;
    private boolean useCaseFailed;
    public GetDetailsOutputData(ArrayList<String> details, boolean useCaseFailed) {
        this.details = details;
        this.useCaseFailed = useCaseFailed;
    }

    public ArrayList<String> getDetails() {
        return details;
    }

}
