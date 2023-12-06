package use_case.get_details;
import entity.Route;

import java.util.List;

public interface GetDetailsDataAccessInterface {
    List<Route> getDetails(String id);
}
