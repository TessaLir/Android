package ru.vetukov.spec.ex_3_allsaintsrecycler.holders;

import android.content.Context;
import android.media.Rating;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import ru.vetukov.spec.ex_3_allsaintsrecycler.R;
import ru.vetukov.spec.ex_3_allsaintsrecycler.adapters.RecyclerSaintAdapter;

public class SaintHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView dob;
    public TextView dod;
    public RatingBar rating;
    public ImageView button;

    private RecyclerSaintAdapter.OnItemClickListener clickListener;
    private RecyclerSaintAdapter.OnItemLongClickListener longClickListener;

    private RecyclerSaintAdapter adapter;

    public SaintHolder(final View itemView
                      ,final RecyclerSaintAdapter adapter
                      ,final RecyclerSaintAdapter.OnItemClickListener clickListener
                      ,final RecyclerSaintAdapter.OnItemLongClickListener longClickListener) {
        super(itemView);

        this.adapter = adapter;
        this.clickListener = clickListener;
        this.longClickListener = longClickListener;

        name = itemView.findViewById(R.id.main_item_text);
        dob = itemView.findViewById(R.id.main_item_dob);
        dod = itemView.findViewById(R.id.main_item_dod);
        rating = itemView.findViewById(R.id.main_item_rating);
        button = itemView.findViewById(R.id.main_item_threedots);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) showPopupMenu(view, position);
            }
        });

        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float newRating, boolean b) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    adapter.ratingChanged(position, newRating);
                }
            }
        });

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Triggers click upwards to the adapter on click
                if (clickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        clickListener.onItemClick(itemView, position);
                    }
                }
            }
        });


        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (clickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        longClickListener.onItemLongClick(itemView, position);
                    }
                    return true;
                }
                return false;
            }
        });

    }

    private void showPopupMenu(View view, final int pos) {
        Context context = view.getContext();
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.inflate(R.menu.context);

        popupMenu
                .setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.context_delete:
                                adapter.remove(pos);
                                return true;
                        }
                        return false;
                    }
                });

        popupMenu.show();
    }

}






