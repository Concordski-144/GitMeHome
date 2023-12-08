package interface_adapter.longitude_latitude;

import use_case.longitude_latitude.LonLatInputBoundary;

public class LonLatController {
    final LonLatInputBoundary lonLatInteractor;
    public LonLatController(LonLatInputBoundary lonLatInteractor) {
        this.lonLatInteractor = lonLatInteractor;
    }
    void execute() {

    }

}
