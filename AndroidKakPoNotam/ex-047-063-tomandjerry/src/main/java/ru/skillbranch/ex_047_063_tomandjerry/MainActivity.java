package ru.skillbranch.ex_047_063_tomandjerry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
        ivTom.animate().alpha(flag ? 1 : 0).setDuration(1500);
        ivJerry.animate().alpha(flag ? 0 : 1).setDuration(1500);
    }

}
