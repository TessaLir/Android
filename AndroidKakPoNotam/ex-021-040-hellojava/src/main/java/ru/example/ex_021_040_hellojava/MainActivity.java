package ru.example.ex_021_040_hellojava;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static int counter = 0;

    private Button btnSubmit;
    private TextView tvResult;
    private TextView tvCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSubmit = findViewById(R.id.main_btn_click);
        tvResult = findViewById(R.id.main_tv_result);
        tvCounter = findViewById(R.id.main_tv_counter);

        btnSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        tvResult.setText("Button Submit!!!");
        tvCounter.setText(String.format("%s", ++counter));
        tvCounter.setTextColor(Color.RED);
        tvCounter.setTextSize(50f);
        tvCounter.setRotation(getRotation(tvCounter.getRotation()));

    }

    private static float getRotation(float size) {
        return size + 90f;
    }

}
