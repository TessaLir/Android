package ru.vetukov.kino.mykinoinfo.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.vetukov.kino.mykinoinfo.R;
import ru.vetukov.kino.mykinoinfo.objects.Film;

public class FilmListAdapter extends RecyclerView.Adapter<FilmListAdapter.FilmHolder> {

    private static List<Film> films;


    public FilmListAdapter(List<Film> films) {
        this.films = films;
    }

    @NonNull
    @Override
    public FilmHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.film_list
                                                                        ,viewGroup
                                                                        ,false);

        return new FilmHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmHolder filmHolder, int i) {
        Film film = films.get(i);

        filmHolder.mLNmae.setText(film.getLocalName());
        filmHolder.mName.setText(film.getName());
        filmHolder.mRating.setText(film.getReting());
        filmHolder.mCCount.setText(film.getCommentCount());


//        final int clickPos = i;
//        filmHolder.mLNmae.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Log.d("FilmListView", "Вы кликнули на позиции: " + clickPos);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    static public class FilmHolder extends RecyclerView.ViewHolder
                                   implements View.OnClickListener {

        private TextView mLNmae;
        private TextView mName;
        private TextView mRating;
        private TextView mCCount;


        public FilmHolder(@NonNull View itemView) {
            super(itemView);

            mLNmae   = itemView.findViewById(R.id.view_text_localname);
            mName    = itemView.findViewById(R.id.view_text_filmname);
            mRating  = itemView.findViewById(R.id.view_text_return);
            mCCount  = itemView.findViewById(R.id.view_text_comments_count);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Film film = films.get(getAdapterPosition());
            String msg = String.format("Вы щелкнули по фильму: %s", film.getName());
            Log.d("FilmListView", msg);
        }
    }
}
