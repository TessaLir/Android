package ru.vetukov.wfms.myuserinterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "$#@%@ MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = findViewById(R.id.main_btn_button);

        b.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        EditText e1 = findViewById(R.id.main_et_one);
        EditText e2 = findViewById(R.id.main_et_two);

        String n1 = e1.getText().toString();
        String n2 = e2.getText().toString();

        Log.d(TAG, String.format("Введены: %s и %s", n1, n2));

        Intent intent = new Intent(this, BackActivity.class);

        intent.putExtra("ONE", n1);
        intent.putExtra("TWO", n2);

        startActivity(intent);

    }
}
