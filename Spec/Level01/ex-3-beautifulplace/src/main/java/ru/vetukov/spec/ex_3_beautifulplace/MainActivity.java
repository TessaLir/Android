package ru.vetukov.spec.ex_3_beautifulplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.snackbar.Snackbar;
import com.viewpagerindicator.UnderlinePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Контейнер для мест
    private List<Place> places = new ArrayList<>();

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Прозрачный статус-бар
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_main);

        // Настраиваем тулбар - определяется в разметке
        Toolbar toolbar = findViewById(R.id.main_tb_toolbar);
        if(toolbar != null) toolbar.setTitle("Beautiful Places");

        // Установка тулбара в качестве экшн-бара
        // при этом экшн-бар скрывется через стиль приложения
        // Но тут еще надо переопределить стиль.
        /*
            <style name="AppTheme.NoActionBar">
            <item name="windowActionBar">false</item>
            <item name="windowNoTitle">true</item>
            </style>
        * */
        setSupportActionBar(toolbar);


        // Находим пейджер
        mPager = findViewById(R.id.main_vp_pager);

        // Добавляем места в список
        // старые картинки не грузились, заменил на котиков, все любят котиков
        places.add(new Place("Монако", "В Столице суверенного княжества Монако живет больше миллионеров, чем настройщиков роялей", "$1180", "$999.95", "https://www.nastol.com.ua/download.php?img=201206/2560x1600/nastol.com.ua-26715.jpg"));
        places.add(new Place("Прага", "Культурная столица восточной европы - город, который хорош в любое время года", "$180", "$80", "https://www.1zoom.me/big2/48/124040-frederika.jpg"));
        places.add(new Place("Таллинн", "Столица прибалтийской жемчужины Эстонии", "$245", "$15", "https://s1.1zoom.ru/big3/214/407444-svetik.jpg"));
        places.add(new Place("Озеро Комо", "Живописное озеро в северной Италии", "$845", "$799", "https://www.travcoa.com/sites/default/files/styles/flexslider_full/public/tours/images/veniceandlakecomo-hero-italy-lake-como-menaggio-41965520.jpg?itok=fROUMZe2"));

        mPagerAdapter = new MyPagerAdapter(places);

        // Устанавливаем адаптер пейджеру
        mPager.setAdapter(mPagerAdapter);

        // Находим инжектор
        UnderlinePageIndicator underlinePageIndicator = findViewById(R.id.main_upi_indicator);
        if (underlinePageIndicator != null) underlinePageIndicator.setViewPager(mPager);

    }


    public void fabClilck(View view) {
        Snackbar.make(findViewById(R.id.main_content_coordinator), "Путешествие заказано", Snackbar.LENGTH_SHORT).show();
    }
}
