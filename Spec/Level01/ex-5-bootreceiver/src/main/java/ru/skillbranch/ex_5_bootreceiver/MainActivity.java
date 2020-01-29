package ru.skillbranch.ex_5_bootreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("happy", "1111");
    }

    public void sendSomething(View view) {
        sendBroadcast(new Intent("SMS"));
    }
}
