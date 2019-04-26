package ru.spec.level_one.vetukov.metro2;

import java.util.ArrayList;

public class Line implements Metro {

    private String name;
    private String color;
    private ArrayList<Metro> stations;

    public Line(String name) {
        this.name = name;
        stations = new ArrayList<>();
    }

    public String getColor() { return color; }

    public String getName() { return name; }

    public ArrayList<Metro> getStations() { return stations; }

    public boolean isStationsEmpty() { return stations.isEmpty(); }

    public int getStationsCount() { return stations.size(); }

    public void addStation(String name) { stations.add(new Station(name)); }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean removeStation(String name) {
        if (stations.contains(name)) {
            stations.remove(name);
            return true;
        }
        return false;
    }

}
