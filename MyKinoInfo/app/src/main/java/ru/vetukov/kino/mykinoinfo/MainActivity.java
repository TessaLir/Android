package ru.vetukov.kino.mykinoinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import ru.vetukov.kino.mykinoinfo.adapters.FilmListAdapter;
import ru.vetukov.kino.mykinoinfo.objects.Film;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Film> films;

    private RecyclerView recycler;
    private FilmListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        films = new ArrayList(){};

        for (int i = 1; i < 15; i++) {
            films.add(new Film("Гарри Поттер " + i
                    , "Harry Potter " + i
                    , "9.5"
                    , 5));
        }

        recycler = findViewById(R.id.view_filmlist);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycler.getContext(),
                    linearLayoutManager.getOrientation());
        recycler.addItemDecoration(dividerItemDecoration);

        recycler.setLayoutManager(linearLayoutManager);
        adapter = new FilmListAdapter(films);

        recycler.setAdapter(adapter);






    }
}
