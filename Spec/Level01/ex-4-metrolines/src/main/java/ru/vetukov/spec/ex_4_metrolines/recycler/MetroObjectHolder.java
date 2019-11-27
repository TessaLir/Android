package ru.vetukov.spec.ex_4_metrolines.recycler;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.recyclerview.widget.RecyclerView;
import ru.vetukov.spec.ex_4_metrolines.R;

public abstract class MetroObjectHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private View view;
    private StationAdapter adapter;

    public MetroObjectHolder(View view, final StationAdapter adapter) {
        super(view);
        this.view = view;
        this.adapter = adapter;
        name = view.findViewById(R.id.element_name);
    }

    public void setName(String name)
    {
        this.name.setText(name);
    }

    public void setBackground(@ColorInt int color)
    {
        view.setBackgroundColor(color);
    }

    protected StationAdapter getAdapter()
    {
        return adapter;
    }

    public abstract void click();
}
