package entity;

public interface RouteFactory {
    Route create(String name, String id, Station[] stations);
}
