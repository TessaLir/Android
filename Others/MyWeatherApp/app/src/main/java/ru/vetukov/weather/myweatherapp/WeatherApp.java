package ru.vetukov.weather.myweatherapp;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApp extends Application {

    private static WeatherService weatherService;
    private Retrofit retrofit;

    public WeatherApp() {
        onCreate();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder().baseUrl("http://api.openweathermap.org")
                                         .addConverterFactory(GsonConverterFactory.create())
                                         .build();

        weatherService = retrofit.create(WeatherService.class);

    }

    public WeatherService getWeatherService() {
        return weatherService;
    }

}
