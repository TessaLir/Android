package ru.vetukov.spec.ex_2_lifecycle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String LOG = "happy";
    public static final int DATA_REQUEST = 444;
    public static final String KEY_HAPPY = "HAPPY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG, "MainActivity onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("happy", "MainActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("happy", "MainActivity onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("happy", "MainActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("happy", "MainActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("happy", "MainActivity onDestroy");
    }

    public void startSecond(View view) {

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(KEY_HAPPY, "hello~!");
        startActivityForResult(intent, DATA_REQUEST);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == DATA_REQUEST) {

            if(data.hasExtra(KEY_HAPPY)) {

                String result = data.getStringExtra(KEY_HAPPY);
                Toast.makeText(this, "Result "+ result, Toast.LENGTH_SHORT).show();

            }

            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
