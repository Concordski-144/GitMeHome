package interface_adapter.longitude_latitude;

import interface_adapter.ViewManagerModel;
import use_case.longitude_latitude.LonLatOutputBoundary;
import use_case.longitude_latitude.LonLatOutputData;

public class LonLatPresenter implements LonLatOutputBoundary {
    private final LonLatViewModel lonLatViewModel;
    private ViewManagerModel viewManagerModel;

    public LonLatPresenter(ViewManagerModel viewManagerModel, LonLatViewModel lonLatViewModel) {
        this.lonLatViewModel = lonLatViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView(LonLatOutputData LonAndLat) {
        LonLatState lonLatState = lonLatViewModel.getState();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
