package ru.spec.level_one.vetukov.metro2;

public class Station implements Metro {

    private String name;

    public Station(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
