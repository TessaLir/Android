package ru.example.ex_021_040_musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static Button btnAdd;
    private static Button btnMinus;

    private static TextView tvItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.main_btn_count_add);
        btnMinus = findViewById(R.id.main_btn_count_minus);

        tvItemCount = findViewById(R.id.main_tv_count);

        btnAdd.setOnClickListener(this);
        btnMinus.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.main_btn_count_add :
                setItemCount(1);
                break;
            case R.id.main_btn_count_minus :
                setItemCount(0);
                break;
        }
    }


    private static void setItemCount(int flag) {
        int count = Integer.parseInt(tvItemCount.getText().toString());
        if (flag == 1) {
            tvItemCount.setText(String.format("%s",++count));
            btnMinus.setClickable(true);
        } else {
            if (count > 0) {
                tvItemCount.setText(String.format("%s",--count));
            } else {
                btnMinus.setClickable(false);
            }
        }
    }
}
