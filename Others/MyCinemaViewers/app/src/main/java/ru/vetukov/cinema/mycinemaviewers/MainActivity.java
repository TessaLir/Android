<<<<<<< HEAD:MyCinemaViewers/app/src/main/java/ru/vetukov/cinema/mycinemaviewers/MainActivity.java
package ru.vetukov.cinema.mycinemaviewers;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;

import java.util.ArrayList;

import retrofit2.Retrofit;
import ru.vetukov.cinema.mycinemaviewers.adapters.CinemaAdapter;
import ru.vetukov.cinema.mycinemaviewers.application.KudaGo;
import ru.vetukov.cinema.mycinemaviewers.objects.Film;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recycler;
    private CinemaAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recycler = findViewById(R.id.view_filmlist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycler.getContext(),
                linearLayoutManager.getOrientation());

        recycler.addItemDecoration(dividerItemDecoration);

        recycler.setLayoutManager(linearLayoutManager);

        adapter = new CinemaAdapter();

        recycler.setAdapter(adapter);


    }




}
=======
package ru.vetukov.cinema.mycinemaviewers;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;

import java.util.ArrayList;

import retrofit2.Retrofit;
import ru.vetukov.cinema.mycinemaviewers.adapters.CinemaAdapter;
import ru.vetukov.cinema.mycinemaviewers.application.KudaGo;
import ru.vetukov.cinema.mycinemaviewers.objects.Film;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Film> films;

    private RecyclerView recycler;
    private CinemaAdapter adapter;

    private static KudaGo go;


    private RecyclerView.Recycler retrifit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        films = new ArrayList(){};

        go = new KudaGo();

        go.getFilmList();

        recycler = findViewById(R.id.view_filmlist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycler.getContext(),
                linearLayoutManager.getOrientation());

        recycler.addItemDecoration(dividerItemDecoration);

        recycler.setLayoutManager(linearLayoutManager);


        new RunAuth().execute();

    }




    class RunAuth extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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


                if (films.size() > 0) break;
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... voids) {

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            adapter = new CinemaAdapter(films, retrifit);

            recycler.setAdapter(adapter);
        }

    }
}
>>>>>>> master-2:Others/MyCinemaViewers/app/src/main/java/ru/vetukov/cinema/mycinemaviewers/MainActivity.java
