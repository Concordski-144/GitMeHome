package use_case.longitude_latitude;

import java.util.ArrayList;

public class LonLatOutputData {
    private final ArrayList<Double> coordinate;
    private boolean useCaseFailed;
    public LonLatOutputData(ArrayList<Double> coordinate, boolean useCaseFailed) {
        this.coordinate = coordinate;
        this.useCaseFailed = useCaseFailed;
    }
    public ArrayList<Double> getCoordinate(){
        return coordinate;
    }
}
