package ru.vetukov.tmp.ex_4_animals;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AnimalHelper extends SQLiteOpenHelper {

    public AnimalHelper(Context context) {
        super(context, "animals.db", null, 1);
    }

    // Вызывается один единственный раз при первом запуске приложения
    @Override
    public void onCreate(SQLiteDatabase db) {
        AnimalTable.onCreate(db);
    }

    // Вызывается единственный раз при апгрейде нашего приложения на новую версию
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        AnimalTable.onUpgrade(db, oldVersion, newVersion);
    }
}
