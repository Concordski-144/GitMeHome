package entity;

public class SubwayRouteFactory implements RouteFactory {
    @Override
    public Route create(String name, String id, double longitude, double latitude, Station[] stations){
        return new SubwayRoute(name, id, longitude, latitude, stations);
    }
}
