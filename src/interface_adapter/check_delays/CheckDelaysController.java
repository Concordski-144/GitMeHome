package interface_adapter.check_delays;

import use_case.check_delays.CheckDelaysInputBoundary;
import use_case.check_delays.CheckDelaysInputData;

public class CheckDelaysController {

    final CheckDelaysInputBoundary checkDelaysUseCaseInteractor;

    public CheckDelaysController(CheckDelaysInputBoundary checkDelaysInteractor) {
        this.checkDelaysUseCaseInteractor = checkDelaysInteractor;
    }

    public void execute(String id, String query_type) {
        CheckDelaysInputData checkDelaysInputData = new CheckDelaysInputData(id, query_type);

        checkDelaysUseCaseInteractor.execute(checkDelaysInputData);
    }
}
