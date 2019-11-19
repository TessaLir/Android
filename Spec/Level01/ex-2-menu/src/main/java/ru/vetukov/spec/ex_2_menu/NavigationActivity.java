package ru.vetukov.spec.ex_2_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottom;
    private TextView hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        bottom = findViewById(R.id.main_nav_navigation);
        hello = findViewById(R.id.main_nav_hello);

        bottom.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.bottom_one :
                hello.setText(getResources().getString(R.string.main_nav_text_one));
                break;
            case R.id.bottom_two :
                hello.setText(getResources().getString(R.string.main_nav_text_two));
                break;
            case R.id.bottom_three :
                hello.setText(getResources().getString(R.string.main_nav_text_three));
                break;
        }

        return false;
    }
}
