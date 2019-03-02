package ru.vetukov.home.myyandextranslate;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TranslateService {
    @GET("/api/v1.5/tr.json/translate")
    Call<Translater> search (
            @Query("key") String key ,
            @Query("text") String text,
            @Query("lang") String lang
    );
}
