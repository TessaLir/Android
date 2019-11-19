package ru.vetukov.spec.ex_1_helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "##&%$ MainActivity";

    private Button btnCheck;
    private Button btnUnCheck;
    private CheckBox cbCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCheck = findViewById(R.id.main_btn_check);
        btnUnCheck = findViewById(R.id.main_btn_uncheck);
        cbCheck = findViewById(R.id.main_ch_check);

        btnCheck.setOnClickListener(this);
        btnUnCheck.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_btn_check :
                cbCheck.setChecked(true);
                Log.d(TAG, "CheckBox is Checked!");
                break;
            case R.id.main_btn_uncheck :
                cbCheck.setChecked(false);
                Log.d(TAG, "CheckBox is UnChecked!");
                break;
        }
    }
}
