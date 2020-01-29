package ru.spec.level_1.ex_5_apps;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class Permissions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView list = (ListView) findViewById(R.id.apps_list);

        // Контейнер для разрешений
        List<String> perms = new ArrayList<>();

        // Получим Intent с которым бала запущена Activity
        Intent intent = getIntent();
        // Проверим, есть ли в Intent что-либо с нужным ключом
        if(intent.hasExtra(MainActivity.PERMISSIONS)) {
            // Получим массив строк (разрешений) из Intent
            String [] p = intent.getStringArrayExtra(MainActivity.PERMISSIONS);
            // Если не нулевой, добавим все что в нем есть
            // в контейнер разрешений
            if(p!= null) {
                Collections.addAll(perms, p);
            }
        }

        // Стандартный адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                // Стандартный layout
                android.R.layout.simple_list_item_1,
                // Идентификатор поля TextView в этом layout
                android.R.id.text1,
                perms
        );
        list.setAdapter(adapter);
    }
}
