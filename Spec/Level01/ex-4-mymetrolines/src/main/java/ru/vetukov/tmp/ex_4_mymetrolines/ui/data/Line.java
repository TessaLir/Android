package ru.vetukov.tmp.ex_4_mymetrolines.ui.data;

import java.util.ArrayList;
import java.util.List;

public final class Line extends Metro {

    private ArrayList<Metro> stations;

    public Line(String name) {
        super(name);
        stations = new ArrayList<>();
    }

    public ArrayList<Metro> getStations() {
        return stations;
    }

    public void setStations(ArrayList<Metro> stations) {
        this.stations = stations;
    }

    public void addStation(Station station) {
        stations.add(station);
    }
}
