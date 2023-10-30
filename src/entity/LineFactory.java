package entity;

public interface LineFactory {
    Line create(String name, String id, double longitude, double latitude, Station[] stations);
}
