package entity;

public class CommonStationFactory implements StationFactory {
    @Override
    public Station create(String name, String id, double longitude, double latitude, boolean accessibility, Integer transitMode, int distance_from_user, Route[] routes){
        return new CommonStation(name, id, longitude, latitude, accessibility, transitMode, distance_from_user, routes);
    }
}
