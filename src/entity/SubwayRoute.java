package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SubwayRoute implements Route {

    private final String name;
    private final String id;
    private final String type = "subway";
    private final Station[] stations;
    private ArrayList<LocalDateTime> departureTimes;
    private ArrayList<Delay> delays = new ArrayList<>();

    SubwayRoute(String name, String id, Station[] stations) {
        this.name = name;
        this.id = id;
        this.stations = stations;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getid() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Station[] getRoute() {
        return stations;
    }

    @Override
    public Station getDestination() {
        return stations[stations.length - 1];
    }

    @Override
    public ArrayList<LocalDateTime> getDepartureTimes() {
        return departureTimes;
    }

    @Override
    public void setDepartureTimes(ArrayList<LocalDateTime> departureTimes) {
        this.departureTimes = departureTimes;
    }

    @Override
    public ArrayList<Delay> getDelays() {
        return delays;
    }

    @Override
    public void setDelays(ArrayList<Delay> delays) {
        this.delays = delays;
    }
}
