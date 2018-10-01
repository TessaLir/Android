package ru.home.vetukov.slqite.test.animals.tables;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class TableHelper extends SQLiteOpenHelper {


    public TableHelper(Context context) {
        super(context
             , "animals.db"
             , null
             , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        AnimalTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        AnimalTable.onUpdate(db);
    }
}
