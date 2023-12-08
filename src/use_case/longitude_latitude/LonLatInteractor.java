package use_case.longitude_latitude;

import interface_adapter.longitude_latitude.LonLatPresenter;

import java.util.ArrayList;
import java.util.HashMap;

public class LonLatInteractor implements LonLatInputBoundary{
    private final LonLatDataAccessInterface dataAccess;

    public LonLatInteractor(LonLatDataAccessInterface dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Override
    public String execute(LonLatInputData inputData) {
        String key = inputData.getKey();
        return getCoordinates(key);
        if (getCoordinates(key) != null) {
            LonLatPresenter.prepareSuccessView();
        }
    }

    private String getCoordinates(String key) {
        HashMap<String, String> coordinatesMap = dataAccess.getCoordinatesMap();
        return coordinatesMap.get(key);
    }
}
