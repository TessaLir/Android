package ru.vetukov.wfms.mylayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "$#@%@ MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(R.layout.my_layout);

        /*
        // Добавление элементов программным образом.
        Button b = new Button(this);
        b.setText("Моя кнопка");
        b.setLayoutParams(new LinearLayout.LayoutParams(dpToPx(200), dpToPx(300)));
        setContentView(b);
        */

        TextView tv = findViewById(R.id.main_tv_title);
        tv.setText("Прам-пам-пам".toUpperCase());

        System.out.println(tv.getText());
        Log.d(TAG, tv.getText().toString());

    }

    private int dpToPx(int dp) {
        float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        return Math.round(dp * scale);
    }

}
