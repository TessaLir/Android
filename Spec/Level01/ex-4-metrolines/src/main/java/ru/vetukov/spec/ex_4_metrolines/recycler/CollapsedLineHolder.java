package ru.vetukov.spec.ex_4_metrolines.recycler;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;


public class CollapsedLineHolder extends MetroObjectHolder implements View.OnClickListener {
    public CollapsedLineHolder(View view, StationAdapter adapter) {
        super(view, adapter);
        view.setOnClickListener(this);
    }

    @Override
    public void click() {
        int position = getAdapterPosition();
        if (position != RecyclerView.NO_POSITION) {
            getAdapter().toggle(position);
        }
    }

    @Override
    public void onClick(View view) {
        click();
    }
}
