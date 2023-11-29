package entity;

public interface StationFactory {
    Station create(String name, String id, double longitude, double latitude, boolean accessibility, int transitMode, int distance_from_user, Route[] routes);
}
