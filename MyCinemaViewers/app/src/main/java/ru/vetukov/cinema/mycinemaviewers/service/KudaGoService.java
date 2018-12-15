package ru.vetukov.cinema.mycinemaviewers.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.vetukov.cinema.mycinemaviewers.examples.CinemaListExample;
import ru.vetukov.cinema.mycinemaviewers.queries.CinemaListQuery;

public interface KudaGoService {
    @GET("/public-api/v1.4/movies/")
    Call<CinemaListExample> getCinemaList (
            @Query("page") int page,
            @Query("page_size") int page_size,
            @Query("fields") String[] fields
    );
}
