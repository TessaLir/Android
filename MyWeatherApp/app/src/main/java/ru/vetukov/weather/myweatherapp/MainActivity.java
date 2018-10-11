package ru.vetukov.weather.myweatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.vetukov.weather.myweatherapp.objects.MoreSearchWeatherObj;
import ru.vetukov.weather.myweatherapp.objects.SingleSearchWeatherObj;

public class MainActivity extends AppCompatActivity {

    private TextView city;
    private TextView temp;
    private TextView description;
    private ImageView image;

    private List<MoreSearchWeatherObj.List01> list = new ArrayList<>();
    private WeatherAdapter adapter;


    Call<SingleSearchWeatherObj> call;
    Call<MoreSearchWeatherObj> callTwo;
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


        callTwo = weather.getWeatherService().getWeather("Moscow"
                ,"metric"
                ,"ru"
                ,"c7d271abe99645d6f5ca56a562688c84");

        call = weather.getWeatherService().getWeatherNow("Moscow"
                                                        ,"metric"
                                                        ,"ru"
                                                        ,"c7d271abe99645d6f5ca56a562688c84");



        callTwo.enqueue(new Callback<MoreSearchWeatherObj>() {
            @Override
            public void onResponse(Call<MoreSearchWeatherObj> call, Response<MoreSearchWeatherObj> response) {
                MoreSearchWeatherObj ex = response.body();

                for (MoreSearchWeatherObj.List01 el : ex.getList()) {

                    list.add(el);
                }


            }

            @Override
            public void onFailure(Call<MoreSearchWeatherObj> call, Throwable t) {
                Log.d("weather","To bad too");
            }
        });


        call.enqueue(new Callback<SingleSearchWeatherObj>() {
            @Override
            public void onResponse(Call<SingleSearchWeatherObj> call, Response<SingleSearchWeatherObj> response) {
                SingleSearchWeatherObj ex = response.body();

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
            public void onFailure(Call<SingleSearchWeatherObj> call, Throwable t) {
                Log.d("weather","To bad");
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        adapter = new WeatherAdapter(this, R.layout.weather_item, list);

        ListView view = findViewById(R.id.weather_items);

        view.setAdapter(adapter);



    }
}
