package ru.vetukov.weather.myweatherapp;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.vetukov.weather.myweatherapp.objects.SingleSearchWeatherObj;

/*
http://api.openweathermap.org
    /data/2.5/weather
        q=Moscow
        units=metric
        lang=ru
        appid=c7d271abe99645d6f5ca56a562688c84
*/

public interface WeatherService {

    // запрос на текущую температуру.
    @GET("/data/2.5/weather")
    Call<SingleSearchWeatherObj> getWeatherNow (
            @Query("q") String q,
            @Query("units") String units,
            @Query("lang") String lang,
            @Query("appid") String appid
    );


}
