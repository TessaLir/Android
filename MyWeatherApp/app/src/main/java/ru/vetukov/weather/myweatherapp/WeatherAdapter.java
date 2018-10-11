package ru.vetukov.weather.myweatherapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.List;

import ru.vetukov.weather.myweatherapp.objects.MoreSearchWeatherObj;

public class WeatherAdapter extends ArrayAdapter<MoreSearchWeatherObj.List01> {

    private List<MoreSearchWeatherObj.List01> list;

    public WeatherAdapter(Context context, int resource, List<MoreSearchWeatherObj.List01> list) {
        super(context, resource);
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.detail_item, parent, false);

        TextView timeView = view.findViewById(R.id.text_time);
        TextView tempView = view.findViewById(R.id.text_temp);

        MoreSearchWeatherObj.List01 li = list.get(position);

        timeView.setText(li.getDtTxt().substring(11, li.getDtTxt().length() - 3));
        tempView.setText(li.getMain().getTemp().toString());

        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
