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
