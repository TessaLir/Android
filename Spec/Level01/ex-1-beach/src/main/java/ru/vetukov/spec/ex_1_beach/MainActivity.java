package ru.vetukov.spec.ex_1_beach;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buyTrip(View view) {
        Snackbar.make(
                findViewById(R.id.main_view_coordinator),
                "Ваше путешествие заказано",
                Snackbar.LENGTH_SHORT
        )
                .show();
    }
}
