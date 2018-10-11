package ru.vetukov.weather.myweatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.vetukov.weather.myweatherapp.objects.Example;
import ru.vetukov.weather.myweatherapp.objects.Weather;

public class MainActivity extends AppCompatActivity {

    private TextView city;
    private TextView temp;
    private TextView description;
    private ImageView image;

    Response response;
    Call<Example> call;
    WeatherApp weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city = findViewById(R.id.text_city);
        temp= findViewById(R.id.text_temp);
        description = findViewById(R.id.text_desc);

        image = findViewById(R.id.image);

        weather = new WeatherApp();

        call = weather.getWeatherService().getWeatherNow("Moscow",
                                                         "metric",
                                                         "ru",
                                                         "c7d271abe99645d6f5ca56a562688c84");

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example ex = response.body();

                if (ex != null) {

                    String desc = ex.getWeather().get(0).getDescription();

                    temp.setText(ex.getMain().getTemp().toString());
                    description.setText(desc);

                    if (desc.equals("ясно")) {
                        image.setImageResource(R.drawable.ic_sun);
                    } else {
                        image.setImageResource(R.drawable.ic_unsun);
                    }
                }

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d("111","111");
            }
        });

    }
}
