package ru.skillbranch.ex_047_063_tomandjerry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int TIME_SIZE = 3000;

    private ImageView ivTom;
    private ImageView ivJerry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivTom = findViewById(R.id.main_iv_tom);
        ivJerry = findViewById(R.id.main_iv_jerry);

        ivTom.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        boolean flag = v.getAlpha() == 0;
        ivTom.animate().alpha(flag ? 1 : 0).setDuration(TIME_SIZE);
        ivTom.animate().scaleX(flag ? 1 : 0).scaleY(flag ? 1 : 0).setDuration(TIME_SIZE);
        ivTom.animate().rotation(flag ? -3600 : 3600).setDuration(TIME_SIZE);
        ivJerry.animate().alpha(flag ? 0 : 1).setDuration(TIME_SIZE);
        ivJerry.animate().scaleX(flag ? 0 : 1).scaleY(flag ? 0 : 1).setDuration(TIME_SIZE);
        ivJerry.animate().rotation(flag ? 3600 : -3600).setDuration(TIME_SIZE);
    }

}
