package use_case.get_details;
import entity.Route;

import java.util.ArrayList;
import java.util.List;

public interface GetDetailsDataAccessInterface {
    ArrayList<String> getDetails(String id, boolean departure);
}
