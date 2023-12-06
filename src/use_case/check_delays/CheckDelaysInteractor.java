package use_case.check_delays;

import entity.Delay;
import entity.Route;
import use_case.check_delays.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckDelaysInteractor implements CheckDelaysInputBoundary {
    final CheckDelaysDataAccessInterface checkDelaysDataAccessObject;
    final CheckDelaysOutputBoundary checkDelaysPresenter;

    public CheckDelaysInteractor(CheckDelaysDataAccessInterface checkDelaysDataAccessObject, CheckDelaysOutputBoundary checkDelaysPresenter) {
        this.checkDelaysDataAccessObject = checkDelaysDataAccessObject;
        this.checkDelaysPresenter = checkDelaysPresenter;
    }


    @Override
    public void execute(CheckDelaysInputData checkDelaysInputData) {
        try {
            if (checkDelaysInputData.getType() == "route") { // check by route
                boolean delayStatus = checkDelaysDataAccessObject.checkDelaysByRoute(checkDelaysInputData.getId());
                CheckDelaysOutputData checkDelaysOutputData = new CheckDelaysOutputData(delayStatus, true);
                checkDelaysPresenter.prepareSuccessView(checkDelaysOutputData);
            } else { // check by station
                boolean delayStatus = checkDelaysDataAccessObject.checkDelaysByStation(checkDelaysInputData.getId());
                CheckDelaysOutputData checkDelaysOutputData = new CheckDelaysOutputData(delayStatus, false);
                checkDelaysPresenter.prepareSuccessView(checkDelaysOutputData);
            }
        } catch (Exception e) {
            e.printStackTrace();
            checkDelaysPresenter.prepareFailView("Error: " + e.getMessage());
        }
    }
}