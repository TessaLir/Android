package ru.vetukov.wfms.myuserinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BackActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);

        Double d1 = 0.0;
        Double d2 = 0.0;
        Double result = 0.0;

        Intent intent = getIntent();

        if (intent != null) {
            d1 = Double.parseDouble(intent.getStringExtra("ONE"));
            d2 = Double.parseDouble(intent.getStringExtra("TWO"));
            result = d1 + d2;
        }

        TextView tvResult = findViewById(R.id.main_tv_result);
        Button btnReturn = findViewById(R.id.main_btn_back);

        tvResult.setText(String.format("%s + %s = %s", d1, d2, result.toString()));
        btnReturn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }

}
