<<<<<<< HEAD:MyCinemaViewers/app/src/main/java/ru/vetukov/cinema/mycinemaviewers/adapters/CinemaAdapter.java
package ru.vetukov.cinema.mycinemaviewers.adapters;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import ru.vetukov.cinema.mycinemaviewers.CinemaInfoActivity;
import ru.vetukov.cinema.mycinemaviewers.R;
import ru.vetukov.cinema.mycinemaviewers.application.KudaGo;
import ru.vetukov.cinema.mycinemaviewers.objects.Film;

public class CinemaAdapter extends RecyclerView.Adapter<CinemaAdapter.CinemaHolder> {

    private List<Film> films;

    private Retrofit retrofit;

    private boolean onLoad;

    private static KudaGo go;

    public CinemaAdapter() {
        this.films = new ArrayList<>();

        go = new KudaGo();

        new RunAuth().execute();

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

        if (i > (films.size() - 5) && !onLoad)
        {
            onLoad = true;
            new RunAuth().execute();
        }

        Film film = films.get(i);

        cinemaHolder.mLNmae.setText(i + " - " + getName(film));
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



    class RunAuth extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            go.getFilmList();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            while(true) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                films.addAll(go.getFilms());


                if (go.isOnStop()) {
                    break;
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... voids) {

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            notifyDataSetChanged();
            onLoad = false;
        }

    }

}
=======
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
>>>>>>> master-2:Others/MyCinemaViewers/app/src/main/java/ru/vetukov/cinema/mycinemaviewers/adapters/CinemaAdapter.java
