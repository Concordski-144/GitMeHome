package entity;

public class CommonLineFactory implements LineFactory {
    @Override
    public Line create(String name, String id, double longitude, double latitude, Station[] stations){
        return new CommonLine(name, id, longitude, latitude, stations);
    }
}
