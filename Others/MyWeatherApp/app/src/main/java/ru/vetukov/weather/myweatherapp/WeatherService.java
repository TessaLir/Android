package ru.vetukov.weather.myweatherapp;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.vetukov.weather.myweatherapp.objects.MoreSearchWeatherObj;
import ru.vetukov.weather.myweatherapp.objects.SingleSearchWeatherObj;

public interface WeatherService {

    // запрос на текущую температуру.
    @GET("/data/2.5/weather")
    Call<SingleSearchWeatherObj> getWeatherNow (
            @Query("q") String q,
            @Query("units") String units,
            @Query("lang") String lang,
            @Query("appid") String appid
    );

    // запрос на несколько дней.
    @GET("/data/2.5/forecast")
    Call<MoreSearchWeatherObj> getWeather (
            @Query("q") String q,
            @Query("units") String units,
            @Query("lang") String lang,
            @Query("appid") String appid
    );

}
