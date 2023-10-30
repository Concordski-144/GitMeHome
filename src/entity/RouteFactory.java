package entity;

public interface RouteFactory {
    Route create(String name, String id, double longitude, double latitude, Station[] stations);
}
