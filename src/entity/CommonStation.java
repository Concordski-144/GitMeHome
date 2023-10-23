package entity;

import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

class CommonStation implements Station {

    private final String name;
    private final String id;
    private final double longitude;
    private final double latitude;
    private final boolean accesibility;
    private final Integer[] transit_modes;
    private double distance_from_user;
    private final Line[] lines;
    private ArrayList<Delay> delays = new ArrayList<Delay>();

    CommonStation(String name, String id, double longitude, double latitude, boolean accesibility, Integer[] transit_modes, Line[] lines) {
        this.name = name;
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.accesibility = accesibility;
        this.transit_modes = transit_modes;
        this.lines = lines;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getid() {
        return this.id;
    }

    @Override
    public double[] getCoordinates() {
        double[] coords = new double[2];
        coords[0] = this.longitude;
        coords[1] = this.latitude;
        return coords;
    }

    @Override
    public boolean getAccesibility() {
        return accesibility;
    }

    @Override
    public Integer[] getTransitModes() {
        return transit_modes;
    }

    @Override
    public double getDistanceFromUser() {
        return distance_from_user;
    }

    @Override
    public Line[] getLines() {
        return lines;
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
