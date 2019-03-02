package ru.home.vetukov.slqite.test.animals.tables;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AnimalTable {

    // Название таблицы
    public static final String TABLE_ANIMALS = "animals";

    // Названия колонок
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ANIMAL = "animal";

    public static final String TABLE_CREATE = String.format("CREATE TABLE %s "
                                                           + "(%s INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                           + " %s TEXT NOR NULL);"
                                                           , TABLE_ANIMALS
                                                           , COLUMN_ID
                                                           , COLUMN_ANIMAL);

    public static void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);

        String[] arrAnimal = { "Donkey", "Cow", "Mouse", "Duck", "Cat", "Goat", "Hamster", "Hen",
                               "Goose", "Horse", "Pig", "Rabbit", "Lion", "Sheep", "Dog", "Bear"};

        for (String el : arrAnimal)
            inservTable(db, el);

    }

    private static void inservTable(SQLiteDatabase db, String name) {
        db.execSQL("INSERT INTO " + TABLE_ANIMALS + " (" + COLUMN_ANIMAL + ") VALUES ('" + name + "')   ");
    }

    public static void onUpdate(SQLiteDatabase db) {
        ///
    }
}
