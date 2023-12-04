package use_case.check_delays;

import entity.Delay;
import entity.Route;
import use_case.check_delays.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckDelaysInteractor implements CheckDelaysInputBoundary {
    final CheckDelaysDataAccessInterface nextDepartureDataAccessObject;
    final CheckDelaysOutputBoundary nextDeparturePresenter;

    public CheckDelaysInteractor(CheckDelaysDataAccessInterface nextDepartureDataAccessObject, CheckDelaysOutputBoundary checkDelaysPresenter) {
        this.nextDepartureDataAccessObject = nextDepartureDataAccessObject;
        this.nextDeparturePresenter = checkDelaysPresenter;
    }


    @Override
    public void execute(CheckDelaysInputData checkDelaysInputData) {
        ArrayList<Delay> delays = nextDepartureDataAccessObject.checkDelaysByRoute(checkDelaysInputData.getId());
        CheckDelaysOutputData nextDepartureOutputData = null;
        HashMap<String, String> delayStatus = null;
        for (Delay delay : delays) {
            String routeDelayStatus = delay.getDelayStatus();
            delayStatus.put(delay.getid(), routeDelayStatus);
        }
        nextDepartureOutputData = new CheckDelaysOutputData(delayStatus, false);
        nextDeparturePresenter.prepareSuccessView(nextDepartureOutputData);
    }
}