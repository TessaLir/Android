package ru.vetukov.spec.ex_3_beautifulplace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyPagerAdapter extends PagerAdapter {

    private List<Place> places;

    public MyPagerAdapter(List<Place> places) {
        this.places = places;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup collection, int position, @NonNull Object view) {
        collection.removeView((View) view);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup collection, int position) {

        Context context = collection.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.page, collection, false);
        Place place = places.get(position);
        String pictureURL = place.getPicture();
        ImageView picture = layout.findViewById(R.id.main_percent_picture);

        Picasso
                .with(context)          // Используя Context
                .load(pictureURL)       // Загружаем картинку по URL
                .fit()                  // Автоматически определяем размеры ImageView
                .centerCrop()           // Масштабируем картинку
                .into(picture);         // в ImageView

        TextView name = layout.findViewById(R.id.main_percent_place);
        name.setText(place.getPlace());
        TextView description = layout.findViewById(R.id.main_percent_description);
        description.setText(place.getDescription());
        TextView priceOld = layout.findViewById(R.id.main_percent_priceold);
        priceOld.setText(place.getOldPrice());
        TextView priceNew = layout.findViewById(R.id.main_percent_pricenew);
        priceNew.setText(place.getNewPrice());

        collection.addView(layout);
        return layout;
    }

    @Override
    public int getCount() {
        return places.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
