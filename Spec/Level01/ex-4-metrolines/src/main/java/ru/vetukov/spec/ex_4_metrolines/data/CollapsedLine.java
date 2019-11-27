package ru.vetukov.spec.ex_4_metrolines.data;

import androidx.annotation.ColorInt;
import ru.vetukov.spec.ex_4_metrolines.R;

public class CollapsedLine extends MetroObject {

    public CollapsedLine(String name, int id, @ColorInt int background) {
        super(name, id, background);
    }

    @Override
    public int getType() {
        return R.layout.line_collapsed;
    }
}
