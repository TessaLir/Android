package ru.vetukov.spec.ex_4_metrolines.data;

import androidx.annotation.ColorInt;

import ru.vetukov.spec.ex_4_metrolines.R;

public class Station extends MetroObject {

    public Station(String name, int id, @ColorInt int background) {
        super(name, id, background);
    }

    @Override
    public int getType() {
        return R.layout.station;
    }
}
