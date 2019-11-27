package ru.vetukov.spec.ex_4_metrolines.recycler;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import ru.vetukov.spec.ex_4_metrolines.R;
import ru.vetukov.spec.ex_4_metrolines.data.CollapsedLine;
import ru.vetukov.spec.ex_4_metrolines.data.ExpandedLine;
import ru.vetukov.spec.ex_4_metrolines.data.MetroObject;

public class StationAdapter extends RecyclerView.Adapter<MetroObjectHolder> {

    private final List<MetroObject> objects;

    private final SparseArray<List<MetroObject>> lineStations;

    public StationAdapter(final List<MetroObject> objects, final SparseArray<List<MetroObject>> lineStations) {
        this.objects = objects;
        this.lineStations = lineStations;
    }

    @Override
    public int getItemViewType(int position) {
        return objects.get(position).getType();
    }

    @NonNull
    @Override
    public MetroObjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return createMetroObjectHolder(parent, viewType, this);
    }

    @NonNull
    private MetroObjectHolder createMetroObjectHolder(ViewGroup parent, int viewType, StationAdapter stationAdapter) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(viewType, parent, false);

        switch (viewType) {
            case R.layout.line_collapsed:
                return new CollapsedLineHolder(view, this);
            case R.layout.line_expanded:
                return new ExpandedLineHolder(view, this);
        }
        return new StationHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MetroObjectHolder holder, int position) {
        MetroObject o = objects.get(position);
        holder.setName(o.getName());
        holder.setBackground(o.getBackground());

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    void toggle(int position) {
        MetroObject line = objects.get(position);

        List<MetroObject> stations = lineStations.get(line.getId());

        switch (line.getType()) {
            case R.layout.line_collapsed:
                objects.addAll(position + 1, stations);
                objects.set(position, new ExpandedLine(line.getName(), line.getId(), line.getBackground()));
                notifyItemChanged(position);
                notifyItemRangeInserted(position + 1, stations.size());
                return;
            case R.layout.line_expanded:
                objects.removeAll(stations);
                objects.set(position, new CollapsedLine(line.getName(), line.getId(), line.getBackground()));
                notifyItemChanged(position);
                notifyItemRangeRemoved(position + 1, stations.size());
                return;
        }
    }
}
