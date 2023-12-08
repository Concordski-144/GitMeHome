package use_case.longitude_latitude;

public interface LonLatOutputBoundary {
    void prepareSuccessView(LonLatOutputData LonAndLat);
    void prepareFailView(String error);
}
