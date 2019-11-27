package ru.vetukov.tmp.ex_4_mymetrolines.ui.recycler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.vetukov.tmp.ex_4_mymetrolines.R;

public class MetroHolder extends RecyclerView.ViewHolder {

    private TextView tvName;
    private ImageView ivImage;
    private RecyclerView rvList;
    private View vItem;
    private MetroAdapter adapter;
    private MetroAdapter.OnItemClickListener listener;

    public MetroHolder(@NonNull final View itemView,
                       MetroAdapter adapter,
                       final MetroAdapter.OnItemClickListener listener ) {
        super(itemView);

        tvName = itemView.findViewById(R.id.metro_name);
        ivImage = itemView.findViewById(R.id.metro_image);
        rvList = itemView.findViewById(R.id.rvMetroStation);

        this.vItem = itemView;
        this.adapter = adapter;
        this.listener = listener;

        vItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(itemView, position);
                    }
                }
            }
        });
    }

    public View getItemView() {
        return vItem;
    }

    public TextView getTvName() {
        return tvName;
    }

    public ImageView getIvImage() {
        return ivImage;
    }

    public RecyclerView getRvList() {
        return rvList;
    }
}
