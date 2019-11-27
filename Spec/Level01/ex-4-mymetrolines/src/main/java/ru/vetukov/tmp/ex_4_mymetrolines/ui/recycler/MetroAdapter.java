package ru.vetukov.tmp.ex_4_mymetrolines.ui.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;

import ru.vetukov.tmp.ex_4_mymetrolines.R;
import ru.vetukov.tmp.ex_4_mymetrolines.ui.data.Line;
import ru.vetukov.tmp.ex_4_mymetrolines.ui.data.Metro;
import ru.vetukov.tmp.ex_4_mymetrolines.ui.data.Station;

public class MetroAdapter extends RecyclerView.Adapter<MetroHolder> {

    private View metroView;
    private OnItemClickListener onClickListener;
    private ArrayList<Metro> mMetro;
    private HashSet<Integer> selected = new HashSet<>();

    public MetroAdapter(ArrayList<Metro> mMetro) {
        this.mMetro = mMetro;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemListener (OnItemClickListener lisener) {
        this.onClickListener = lisener;
    }

    public boolean hasSelected() {
        return selected.isEmpty();
    }

    public boolean isSelected(int position) {
        return selected.contains(position);
    }

    public void addSelected(int position) {
        selected.add(position);
    }

    public void removeSelected(int position) {
        selected.remove(position);
    }

    public void toggleSelection(int position) {
        if (isSelected(position)) selected.remove(position);
        else selected.add(position);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MetroHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        switch (viewType) {
            case 0 :
                metroView = inflater.inflate(R.layout.metro_item, parent, false);
                break;
            case 1 :
                metroView = inflater.inflate(R.layout.metro_selecteditem, parent, false);
                break;
            case 2 :
                metroView = inflater.inflate(R.layout.metro_item_station, parent, false);
                break;
        }

        return new MetroHolder(metroView, this, onClickListener);
    }

    @Override
    public int getItemViewType(int position) {
        if (mMetro.get(position) instanceof Station) return 2;
        else if (isSelected(position)) return 1;
        else return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull MetroHolder holder, int position) {
        Metro metro = mMetro.get(position);
        holder.getTvName().setText(metro.getName());
        if (metro instanceof Line) {
            holder.getIvImage().setColorFilter(metro.getColor());
            if (isSelected(position)) {
                RecyclerView recycler = holder.getRvList();
                MetroAdapter adapter = new MetroAdapter(((Line) metro).getStations());
                recycler.setLayoutManager(new LinearLayoutManager(recycler.getContext()));
                recycler.setAdapter(adapter);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mMetro.size();
    }
}
