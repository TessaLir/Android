package ru.vetukov.spec.ex_2_saverestorestate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String COUNTER_KEY = "COUNTER_KEY";
    private int counter = 0;
    private CheckBox cbCheckOne;
    private CheckBox cbCheckTwo;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbCheckOne = findViewById(R.id.main_cb_one);
        cbCheckTwo = findViewById(R.id.main_cb_two);
        tvResult = findViewById(R.id.main_tv_counter);

        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt(COUNTER_KEY, 0);
        }

        cbCheckOne.setOnClickListener(this);
        cbCheckTwo.setOnClickListener(this);

        printCounter();

    }

    @Override
    public void onClick(View v) {
        counter++;
        printCounter();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COUNTER_KEY, counter);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void printCounter() {
        tvResult.setText("" + counter);
    }
}
