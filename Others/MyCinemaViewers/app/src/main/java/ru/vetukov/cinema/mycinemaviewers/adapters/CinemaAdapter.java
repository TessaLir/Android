package ru.vetukov.cinema.mycinemaviewers.adapters;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import retrofit2.Retrofit;
import ru.vetukov.cinema.mycinemaviewers.CinemaInfoActivity;
import ru.vetukov.cinema.mycinemaviewers.R;
import ru.vetukov.cinema.mycinemaviewers.objects.Film;

public class CinemaAdapter extends RecyclerView.Adapter<CinemaAdapter.CinemaHolder> {

    private List<Film> films;
    private Retrofit mRetrofit;

    public CinemaAdapter(List<Film> films, Retrofit retrofit) {
        this.films = films;
        this.mRetrofit = retrofit;
    }

    @NonNull
    @Override
    public CinemaHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.items_cinema
                                                                       ,viewGroup
                                                                       ,false);

        return new CinemaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CinemaHolder cinemaHolder, int i) {
        Film film = films.get(i);

        cinemaHolder.mLNmae.setText(getName(film));
        cinemaHolder.mName.setText(film.getLocalName());
        cinemaHolder.mRating.setText(film.getReting());
        cinemaHolder.mCCount.setText(film.getCommentCount());
    }

    private String getName(Film film) {
        return (film.getName().length() < 25)
                ? film.getName()
                : film.getName().substring(0, 24) + "...";
    }

    @Override
    public int getItemCount() {
        return films.size();
    }


    public class CinemaHolder extends RecyclerView.ViewHolder
                              implements View.OnClickListener {

        private TextView mLNmae;
        private TextView mName;
        private TextView mRating;
        private TextView mCCount;

        public CinemaHolder(@NonNull View itemView) {
            super(itemView);

            mLNmae   = itemView.findViewById(R.id.item_text_lozalized);
            mName    = itemView.findViewById(R.id.item_text_name);
            mRating  = itemView.findViewById(R.id.item_text_reting);
            mCCount  = itemView.findViewById(R.id.item_text_comments_count);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Film film = films.get(getAdapterPosition());
            String msg = String.format("Вы щелкнули по фильму: %s", getName(film));
            Log.d("CinemaAdapter", msg);

            Intent intent = new Intent(v.getContext(), CinemaInfoActivity.class);
            intent.putExtra("FILM", film);
            v.getContext().startActivity(intent);
        }
    }
}
