package ru.vetukov.spec.ex_2_lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d("happy", "SecondActivity onCreate");

        edit = findViewById(R.id.main_second_edit);

        Intent intent = getIntent();
        if(intent.hasExtra(MainActivity.KEY_HAPPY)) {
            String hello = intent.getStringExtra(MainActivity.KEY_HAPPY);
        }

    }

    @Override
    public void onBackPressed() {
        Intent data = new Intent();

        // получить текст из EditText
        String text = edit.getText().toString();
        // добавить его в Intent по ключу
        data.putExtra(MainActivity.KEY_HAPPY, text);

        setResult(RESULT_OK, data);
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("happy", "SecondActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("happy", "SecondActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("happy", "SecondActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("happy", "SecondActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("happy", "SecondActivity onDestroy");
    }

}
