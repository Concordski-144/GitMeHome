package use_case.check_delays;

import entity.CommonDelayFactory;
import entity.Delay;
import entity.DelayFactory;
import entity.Route;
import use_case.check_delays.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckDelaysInteractor implements CheckDelaysInputBoundary {
    final CheckDelaysDataAccessInterface checkDelaysDataAccessObject;
    final CheckDelaysOutputBoundary checkDelaysPresenter;
    final DelayFactory delayFactory = new CommonDelayFactory();

    public CheckDelaysInteractor(CheckDelaysDataAccessInterface checkDelaysDataAccessObject, CheckDelaysOutputBoundary checkDelaysPresenter) {
        this.checkDelaysDataAccessObject = checkDelaysDataAccessObject;
        this.checkDelaysPresenter = checkDelaysPresenter;
    }


    @Override
    public void execute(CheckDelaysInputData checkDelaysInputData) {
        try {
            if (checkDelaysInputData.getType() == "route") { // check by route
                boolean delayStatus = checkDelaysDataAccessObject.checkDelaysByRoute(checkDelaysInputData.getId());
                String routeName = checkDelaysDataAccessObject.getRouteName();
                CheckDelaysOutputData checkDelaysOutputData = new CheckDelaysOutputData(delayStatus, routeName, true);
                checkDelaysPresenter.prepareSuccessView(checkDelaysOutputData);
            } else { // check by station
                boolean delayStatus = checkDelaysDataAccessObject.checkDelaysByStation(checkDelaysInputData.getId());
                String stationName = checkDelaysDataAccessObject.getStationName();
                CheckDelaysOutputData checkDelaysOutputData = new CheckDelaysOutputData(delayStatus, stationName, false);
                checkDelaysPresenter.prepareSuccessView(checkDelaysOutputData);
            }
        } catch (Exception e) {
            e.printStackTrace();
            checkDelaysPresenter.prepareFailView("Error: " + e.getMessage());
        }
    }
}