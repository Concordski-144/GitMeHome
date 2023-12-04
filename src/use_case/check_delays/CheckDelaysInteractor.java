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
        //temporarily disabled due to bug in DAO
//        ArrayList<Delay> delays = checkDelaysDataAccessObject.checkDelaysByRoute(checkDelaysInputData.getId());

//        CheckDelaysOutputData checkDelaysOutputData = null;
//        HashMap<String, String> delayStatus = null;
//        for (Delay delay : delays) {
//            String routeDelayStatus = delay.getDelayStatus();
//            delayStatus.put(delay.getid(), routeDelayStatus);
//        }
        try {
            boolean delayStatus = checkDelaysDataAccessObject.checkDelaysByStation(checkDelaysInputData.getId());
            CheckDelaysOutputData checkDelaysOutputData = new CheckDelaysOutputData(delayStatus, false);
            checkDelaysPresenter.prepareSuccessView(checkDelaysOutputData);
        } catch (Exception e) {
            e.printStackTrace();
            checkDelaysPresenter.prepareFailView("Error: " + e.getMessage());
        }

    }
}