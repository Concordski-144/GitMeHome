package entity;

public interface StationFactory {
    Station create(String name, String id, double longitude, double latitude, boolean accessibility, Integer[] transitModes, int distance_from_user, Route[] routes);
}
