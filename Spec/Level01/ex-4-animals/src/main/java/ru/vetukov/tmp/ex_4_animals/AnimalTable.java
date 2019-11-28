package ru.vetukov.tmp.ex_4_animals;

import android.database.sqlite.SQLiteDatabase;

public class AnimalTable {

    // Название таблицы
    public static final String TABLE_ANIMALS = "animals";

    // Названия колонок
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ANIMAL = "animal";

    public static final String TABLE_CREATE = "   CREATE TABLE ANIMALS    "
                                            + "   (_id INTEGER PRIMARY KEY AUTOINCREMENT    "
                                            + "   , animal TEXT NOT NULL ) ;     ";

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);

        db.execSQL("insert into animals (animal) values('leo')        ");
        db.execSQL("insert into animals (animal) values('crocodile')  ");
        db.execSQL("insert into animals (animal) values('raven')      ");
        db.execSQL("insert into animals (animal) values('lizard')     ");
        db.execSQL("insert into animals (animal) values('orca')       ");
        db.execSQL("insert into animals (animal) values('dog')        ");
        db.execSQL("insert into animals (animal) values('cat')        ");
        db.execSQL("insert into animals (animal) values('bird')       ");
        db.execSQL("insert into animals (animal) values('fish')       ");
        db.execSQL("insert into animals (animal) values('penguin')    ");
        db.execSQL("insert into animals (animal) values('octopus')    ");

    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // db.execSQL("alter table animals add column ...");
        // db.execSQL("drop table ...");
    }

}
