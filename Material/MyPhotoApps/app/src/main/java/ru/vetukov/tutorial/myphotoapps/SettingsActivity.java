package ru.vetukov.tutorial.myphotoapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.setting_button_primary,
              R.id.setting_button_brown,
              R.id.setting_button_pink,
              R.id.setting_button_yellow})
    void onClick(View view) {

        String msg = "";

        switch (view.getId()) {
            case R.id.setting_button_primary:
                msg = "primary";
                break;
            case R.id.setting_button_brown:
                msg = "brown";
                break;
            case R.id.setting_button_pink:
                msg = "pink";
                break;
            case R.id.setting_button_yellow:
                msg = "yellow";
                break;
            default :
                msg = "111";
                break;
        }

        Log.d("happy", msg);
    }
}
