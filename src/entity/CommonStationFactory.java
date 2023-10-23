package entity;

import java.time.LocalDateTime;

public class CommonStationFactory implements StationFactory {
    @Override
    public Station create(String name, String id, double longitude, double latitude, boolean accessibility, Integer[] transitModes, Line[] lines){
        return new CommonStation(name, id, longitude, latitude, accessibility, transitModes, lines);
    }
}
