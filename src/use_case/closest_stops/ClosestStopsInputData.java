package use_case.closest_stops;

public class ClosestStopsInputData {

    final private double longitude;
    final private double latitude;
    final private int numberOfStopsDesired;

    public ClosestStopsInputData(double longitude, double latitude, int numberOfStopsDesired) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.numberOfStopsDesired = numberOfStopsDesired;
    }

    double[] getCoordinates() {
        double[] coords = new double[2];
        coords[0] = this.longitude;
        coords[1] = this.latitude;
        return coords;
    }

    int getNumberOfStopsDesired() {
        return numberOfStopsDesired;
    }
}
