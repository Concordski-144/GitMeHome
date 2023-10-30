package entity;

public class SubwayRouteFactory implements RouteFactory {
    @Override
    public Route create(String name, String id, Station[] stations){
        return new SubwayRoute(name, id, stations);
    }
}
