package ru.spec.level_one.vetukov.metro2;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class MetroAdapter extends RecyclerView.Adapter<MetroAdapter.ViewHolder> {

    private boolean isStation;
    private View metroView;
    private OnItemClickListener onClickListener;
    private ArrayList<Metro> mMetro;
    private HashSet<Integer> selected = new HashSet<>();


    public static interface OnItemClickListener {
        public void onItemClick(View itemView, int position);
    }

    public void setOnItemListener (OnItemClickListener lisener) {
        this.onClickListener = lisener;
    }

    public MetroAdapter(ArrayList<Metro> mMetro, boolean isStation) {
        this.mMetro = mMetro;
        this.isStation = isStation;
    }

    public MetroAdapter(ArrayList<Metro> mMetro) {
        this.mMetro = mMetro;
        this.isStation = false;
    }

    public void toggleSelection(int position) {
        if (isSelected(position)) {
            selected.remove(position);
        }
        else {
            selected.add(position);
        }
        notifyItemChanged(position);
    }

    public boolean hasSelected() {
        return selected.isEmpty();
    }

    public void addSelected(int position) {
        selected.add(position);
    }

    public boolean isSelected(int position) {
        return selected.contains(position);
    }

    public void removeSelected(int position) {
        selected.remove(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        if (viewType == 0)
            metroView = inflater.inflate(R.layout.metro_item, parent, false);
        if (viewType == 1)
            metroView = inflater.inflate(R.layout.metro_selecteditem, parent, false);
        if (viewType == 22)
            metroView = inflater.inflate(R.layout.metro_item_station, parent, false);

        ViewHolder holder = new ViewHolder(metroView, this, onClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Metro tmpMetro = mMetro.get(position);
        TextView textView = viewHolder.nameTextView;

        if (tmpMetro instanceof Station) {
            Station st = (Station)tmpMetro;
            textView.setText(st.getName());
        }

        if (tmpMetro instanceof Line) {
            Line line = (Line)tmpMetro;
            textView.setText(line.getName());
            ImageView imageView = viewHolder.imageView;
            imageView.setColorFilter(getColor(line.getColor()));

            if (isSelected(position)) {
                RecyclerView rvMetro = viewHolder.getItemView().findViewById(R.id.rvMetroStation);
                MetroAdapter adapter = new MetroAdapter(line.getStations(), true);
                rvMetro.setLayoutManager(new LinearLayoutManager(rvMetro.getContext()));
                rvMetro.setAdapter(adapter);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mMetro.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isStation) return 22;
        if (isSelected(position))
            return 1;
        else
            return 0;
    }

    //------------------------------------------------------------------------------------
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTextView;
        private ImageView imageView;
        private View itemView;
        private MetroAdapter adapter;
        private MetroAdapter.OnItemClickListener onItemClickListener;

        public View getItemView() {
            return itemView;
        }

        public ViewHolder(@NonNull final View itemView,
                          final MetroAdapter adapter,
                          final MetroAdapter.OnItemClickListener onItemClickListener) {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.metro_name);
            imageView = (ImageView) itemView.findViewById(R.id.metro_image);

            this.itemView = itemView;
            this.adapter = adapter;
            this.onItemClickListener = onItemClickListener;

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                if (onItemClickListener != null) {
                    int position = getAdapterPosition();

                    if (position != RecyclerView.NO_POSITION) {
                        onItemClickListener.onItemClick(itemView, position);
                    }
                }
                }
            });
        }
    }

    private int getColor(String color) {
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
            case "#CCCCFF" :
                return 0xFFCCCCFF;
        }
        return Color.BLACK;
    }
}
