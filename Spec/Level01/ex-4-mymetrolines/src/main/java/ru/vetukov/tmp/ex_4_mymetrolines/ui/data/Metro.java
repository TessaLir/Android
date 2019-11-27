package ru.vetukov.tmp.ex_4_mymetrolines.ui.data;

import android.graphics.Color;

public abstract class Metro {

    private String name;
    private String color;

    public Metro(String name) {
        this.name = name;
        this.color = "#FFFFFF";
    }

    public Metro(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return getColorInt(color);
    }

    public void setColor(String color) {
        this.color = color;
    }

    private int getColorInt(String color) {
        switch (color) {
            case "#900020" :
                return 0xFF900020;
            case "#34C924" :
                return 0xFF34C924;
            case "#3E5F8A" :
                return 0xFF3E5F8A;
            case "#6495ED" :
                return 0xFF6495ED;
            case "#64400F" :
                return 0xFF64400F;
            case "#CD7F32" :
                return 0xFFCD7F32;
            case "#702963" :
                return 0xFF702963;
            case "#FFDC33" :
                return 0xFFFFDC33;
            case "#B5B8B1" :
                return 0xFFB5B8B1;
            case "#7FFFD4" :
                return 0xFF7FFFD4;
            default :
                return 0xFFCCCCFF;
        }
    }

}
