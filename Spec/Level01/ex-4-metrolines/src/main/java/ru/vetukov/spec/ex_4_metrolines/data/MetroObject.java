package ru.vetukov.spec.ex_4_metrolines.data;

import androidx.annotation.ColorInt;

public abstract class MetroObject {
    private String name;
    private int id;
    private int background;

    public MetroObject(String name, int id, @ColorInt int background) {
        this.name = name;
        this.id  = id;
        this.background = background;
    }

    public String getName() {
        return name;
    }

    public abstract int getType();


    public int getBackground() {
        return background;
    }

    public int getId() {
        return id;
    }
}
