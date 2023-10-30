package entity;

public interface StationFactory {
    Station create(String name, String id, double longitude, double latitude, boolean accessibility, Integer[] transitModes, Route[] routes);
}
