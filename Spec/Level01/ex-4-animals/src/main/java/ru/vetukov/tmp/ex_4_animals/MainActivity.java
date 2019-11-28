package ru.vetukov.tmp.ex_4_animals;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    private SimpleCursorAdapter adapter;
    private AnimalHelper helper;

    private String orderBy = "animal asc";
    private String likeQuery = "";
    private String selection = null;
    private String [] selectionArgs = null;

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.main_list);
        helper = new AnimalHelper(this);

        adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                null,
                new String[] { AnimalTable.COLUMN_ANIMAL },
                new int[] { android.R.id.text1 }
        );

        list.setAdapter(adapter);
        updateCursor();
        registerForContextMenu(list);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        int pos = info.position;
        switch (item.getItemId()) {
            case R.id.context_delete:
                deleteAnAnimal(pos);
                return true;
            case R.id.context_update:
                showUpdateDialog(pos);
                return true;
        }

        return super.onContextItemSelected(item);
    }

    private void showUpdateDialog(int pos) {
        Cursor cursor = adapter.getCursor();

        cursor.moveToPosition(pos);

        final int databaseId = cursor.getInt(cursor.getColumnIndex(AnimalTable.COLUMN_ID));
        final String animal = cursor.getString(cursor.getColumnIndex(AnimalTable.COLUMN_ANIMAL));
        final EditText dialog = (EditText) getLayoutInflater().inflate(R.layout.dialog, null);

        dialog.setText(animal);
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b
                .setTitle("Update An Animal!")
                .setView(dialog);

        b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        b.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface d, int which) {
                String animal = dialog.getText().toString();
                // TextUtils проверяет и на null и на пустую строку
                if(!TextUtils.isEmpty(animal)) {
                    updateAnimal(databaseId, animal);
                }
                d.dismiss();
            }
        });
        b.create().show();

    }

    private void updateAnimal(int databaseId, String animal) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(AnimalTable.COLUMN_ANIMAL, animal);

        db.update(
                AnimalTable.TABLE_ANIMALS,
                values,
                AnimalTable.COLUMN_ID + " = ?",
                new String[]{
                        Integer.toString(databaseId)
                }
        );

        updateCursor();
    }

    private void deleteAnAnimal(int pos) {
        Cursor cursor = adapter.getCursor();
        cursor.moveToPosition(pos);
        int databaseId = cursor.getInt(cursor.getColumnIndex(AnimalTable.COLUMN_ID));
        // 1. удалить строку из таблицы базы данных
        SQLiteDatabase db = helper.getWritableDatabase();
//        db.delete(
//                AnimalsTable.TABLE_ANIMALS,
//                "_id="+databaseId, // 13; drop table animals;
//                null
//        );
//
//        // delete from animals where _id=          13; drop table animals;
//
        db.delete(
                AnimalTable.TABLE_ANIMALS,
                AnimalTable.COLUMN_ID+ " = ?",
                new String[]{
                        Integer.toString(databaseId)
                }
        );
        // delete from animals where _id=          '13; drop table animals;'
        // 2. что-то еще?
        updateCursor();
    }

    private void updateCursor() {
        Cursor cursor = helper.getReadableDatabase().query(
                AnimalTable.TABLE_ANIMALS,
                null,
                selection,
                selectionArgs,
                null,
                null,
                orderBy,
                null
        );

        adapter.swapCursor(cursor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_down:
                orderBy = "animal desc";
                updateCursor();
                return true;
            case R.id.main_up:
                orderBy = "animal asc";
                updateCursor();
                return true;
            case R.id.main_add:
                addAnimalDialog();
                return true;
            case R.id.main_search:
                handleSearch(item);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void handleSearch(MenuItem item) {
        searchView = (SearchView) item.getActionView();
        item.expandActionView();
        searchView.setQuery(likeQuery, false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                likeQuery = s;
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(!TextUtils.isEmpty(s)) {
                    // select * from animals where animal like '%a%'
                    selection = AnimalTable.COLUMN_ANIMAL + "  like ?  ";
                    selectionArgs = new String[ ] {
                            "%" + s + "%"
                    };
                }
                else {
                    selection = null;
                    selectionArgs = null;
                }
                likeQuery = "";
                updateCursor();
                return true;
            }
        });
    }

    private void addAnimalDialog() {
        final EditText dialog = (EditText) getLayoutInflater().inflate(R.layout.dialog, null);
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b
                .setTitle("Add An Animal!")
                .setView(dialog);

        b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        b.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface d, int which) {
                String animal = dialog.getText().toString();
                // TextUtils проверяет и на null и на пустую строку
                if(!TextUtils.isEmpty(animal)) {
                    addAnimal(animal);
                }
                d.dismiss();
            }
        });
        b.create().show();
    }

    private void addAnimal(String animal) {
        // 1. Добавить животное в таблицу
        SQLiteDatabase db = helper.getWritableDatabase();

        // по ключу - "название колонки" добавить значение
        ContentValues values = new ContentValues();
        values.put(AnimalTable.COLUMN_ANIMAL, animal);

        db.insert(
                AnimalTable.TABLE_ANIMALS,
                null,
                values
        );
        // 2. Сделать что-то еще?
        updateCursor();
    }
}
