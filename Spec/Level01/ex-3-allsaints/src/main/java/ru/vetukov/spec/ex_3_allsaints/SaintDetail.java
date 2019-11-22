package ru.vetukov.spec.ex_3_allsaints;

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

    private Saint s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saint_detail);

        // Находим элементы управления
        mSaintsWebView = findViewById(R.id.main_detail_saint_detail);
        mSaintRating = findViewById(R.id.main_detail_rating);

        mSaintsWebView.setWebViewClient(new WebViewClient());
        s = new Saint("","","",0f);

        Intent intent = getIntent();
        if (intent != null) {
            // получаем из Intent передаваемые параметры
            s.setName(intent.getExtras().getString(MainActivity.SAINT_NAME));

            if (s.getName() != null) {
                // формируем URL для википедии
                String saint = s.getName().replace(" ", "_");
                String url = "https://en.m.wikipedia.org/wiki/" + saint;
                mSaintsWebView.loadUrl(url);
            }

            // В начале проверяем, есть ли такое значение
            if (intent.hasExtra(MainActivity.SAINT_RATING)) {
                s.setRating(intent.getExtras().getFloat(MainActivity.SAINT_RATING));
                mSaintRating.setRating(s.getRating());
            }

            if (intent.hasExtra(MainActivity.SAINT_ID)) {
                mSaintId = intent.getExtras().getInt(MainActivity.SAINT_ID);

            }
        }
    }

    // По нажатию на клавишу Back


    @Override
    public void onBackPressed() {

        Intent intent = getIntent();

        intent.putExtra(MainActivity.SAINT_ID, mSaintId);
        intent.putExtra(MainActivity.SAINT_RATING, mSaintRating.getRating());

        setResult(MainActivity.RATING_REQUEST, intent);

        // Вызываем onBackPressed суперкласса, закрывая Activity
        super.onBackPressed();
    }
}
