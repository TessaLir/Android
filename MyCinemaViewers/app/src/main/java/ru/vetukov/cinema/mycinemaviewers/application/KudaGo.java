package ru.vetukov.cinema.mycinemaviewers.application;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.vetukov.cinema.mycinemaviewers.examples.CinemaListExample;
import ru.vetukov.cinema.mycinemaviewers.objects.Film;
import ru.vetukov.cinema.mycinemaviewers.service.KudaGoService;

public class KudaGo extends Application {

    public static final int FILM_SIZE_LOAD = 100;

    public static int filmsPage = 0;

    private static KudaGoService service;
    private Retrofit retrofit;

    private List<Film> films;


    public KudaGo() {

        films = new ArrayList<>();

        retrofit = new Retrofit.Builder().baseUrl("https://kudago.com/")
                                         .addConverterFactory(GsonConverterFactory.create())
                                         .build();

        service = retrofit.create(KudaGoService.class);
    }

    public void getFilmList() {
//String localName, String name, String date, String imageSRC, String description, String reting, int commentCount
        String[] arr = new String[] {
                "title,original_title,id,comments_count,year,poster,body_text"
        };

        Call<CinemaListExample> call = service.getCinemaList(++filmsPage, FILM_SIZE_LOAD, arr);
        call.enqueue(new MyAsync<CinemaListExample>());
    }


    private class MyAsync<T>  implements Callback<T> {

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            T example = response.body();

            if (example instanceof CinemaListExample) {
                List<CinemaListExample.Result> list = ((CinemaListExample) example).getResults();

                for (CinemaListExample.Result el : list) {
                    films.add(new Film(el.getOriginalTitle()
                            , el.getTitle()
                            , el.getYear().toString()
                            , el.getPoster().getImage()
                            , el.getBody_text()
                            , "9.5"
                            , el.getCommentsCount()));
                }
            }

        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            Log.d("", "");
        }
    }


    public List<Film> getFilms() {
        return films;
    }
}
