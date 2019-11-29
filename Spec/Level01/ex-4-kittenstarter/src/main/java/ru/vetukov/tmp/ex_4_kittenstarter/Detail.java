package ru.vetukov.tmp.ex_4_kittenstarter;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Расширим окно Activity 
        // как только возможно
        /*
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        */

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        // Не хотим заголовка
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detail);
        ImageView image = (ImageView) findViewById(R.id.main_iv_detail);

        // Получим Intent с помощью которого
        // запустили
        Intent intent = getIntent();
        if(intent.hasExtra(MainActivity.IMAGE_URL)) {
            String url = intent.getStringExtra(MainActivity.IMAGE_URL);
            if(url != null) {
                // "Маленькая" картинка имеет в конце URL
                // _q.jpg  , "большая" картинка имеет
                // в конце _h.jpg
                url = url.replace("_q.jpg", "_h.jpg");
                Picasso.with(this)
                        .load(url)
                        .fit()
                        .centerCrop()
                        .into(image);
           }
        }
    }
}
