package ru.vetukov.spec.ex_2_allsaintsstarter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RatingBar;

public class SaintDetail extends AppCompatActivity {

    private WebView mSaintsWebView;
    private RatingBar mSaintRating;
    private int mSaintId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saint_detail);

        // Находим элементы управления
        mSaintsWebView = findViewById(R.id.main_detail_saint_detail);
        mSaintRating = findViewById(R.id.main_detail_rating);

        mSaintsWebView.setWebViewClient(new WebViewClient());

        Intent intent = getIntent();
        if (intent != null) {
            // получаем из Ibtent передаваемые параметры

            ///
            String saint = intent.getExtras().getString(MainActivity.SAINT_NAME);
            ///

            if (saint != null) {
                // формируем URL для википедии
                saint = saint.replace(" ", "_");
                String url = "https://en.m.wikipedia.org/wiki/" + saint;
                mSaintsWebView.loadUrl(url);
            }

            // В начале проверяем, есть ли такое значение
            if (intent.hasExtra(MainActivity.SAINT_RATING)) {
                ///
                ///
            }

            if (intent.hasExtra(MainActivity.SAINT_ID)) {
                ///
                ///
            }
        }
    }

    // По нажатию на клавишу Back


    @Override
    public void onBackPressed() {

        // Формирум Intent, который будет возращен в вызываемую нас Activity
        ///
        Intent intent = null;

        // Добавляем а Intent нужные параметры
        ///
        ///

        // Устанавливаем результат
        ///
        ///

        // Вызываем onBackPressed суперкласса, закрывая Activity
        super.onBackPressed();
    }
}
