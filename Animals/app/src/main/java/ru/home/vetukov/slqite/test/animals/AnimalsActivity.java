package ru.home.vetukov.slqite.test.animals;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import ru.home.vetukov.slqite.test.animals.tables.AnimalTable;
import ru.home.vetukov.slqite.test.animals.tables.TableHelper;

public class AnimalsActivity extends AppCompatActivity {

    private ListView list;
    private SimpleCursorAdapter adapter;
    private TableHelper helper;

    SearchView searchView;

    private boolean orderByFlag = true;
    private String orderBy = "animal asc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals);

        list = findViewById(R.id.list);
        helper = new TableHelper(this);

        adapter = new SimpleCursorAdapter(this,
                                          android.R.layout.simple_list_item_1,
                                          null,
                                          new String[] { AnimalTable.COLUMN_ANIMAL },
                                          new int[] { android.R.id.text1 });

        list.setAdapter(adapter);

        updateCursor();

        registerForContextMenu(list);
    }

    private String selection = null;
    private String[] selectionArgs = null;

    private void sortedList() {
        if (orderByFlag)
            orderBy = "animal desc";
        else
            orderBy = "animal asc";

        orderByFlag = !orderByFlag;
    }

    private void addAnimalDialog() {
        final EditText dialog = (EditText) getLayoutInflater().inflate(R.layout.dialog, null);
        AlertDialog.Builder b = new AlertDialog.Builder(this);

        b
                .setTitle("Add an Animal!")
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

                if (!TextUtils.isEmpty(animal)) {
                    addAnimal(animal);
                }
                d.dismiss();
            }
        });

        b.create().show();

    }

    private void addAnimal(String animal) {

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(AnimalTable.COLUMN_ANIMAL, animal);

        db.insert(AnimalTable.TABLE_ANIMALS,
                  null,
                  value);

        updateCursor();

    }

    private void deleteAnimals(int position) {
        Cursor cursor = adapter.getCursor();
        cursor.moveToPosition(position);

        int databaseId = cursor.getInt(cursor.getColumnIndex(AnimalTable.COLUMN_ID));

        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(
                AnimalTable.TABLE_ANIMALS,
                AnimalTable.COLUMN_ID + " = ?",
                new String[] { Integer.toString(databaseId) }
        );

        updateCursor();
    }

    private void showUpdateAnimal(int position) {
        Cursor cursor = adapter.getCursor();

        cursor.moveToPosition(position);

        final int databaseId = cursor.getInt(
                cursor.getColumnIndex(AnimalTable.COLUMN_ID)
        );

        String animal = cursor.getString(
                cursor.getColumnIndex(AnimalTable.COLUMN_ANIMAL)
        );

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
                if(!TextUtils.isEmpty(animal))
                {
                    updateAnanimal(databaseId, animal);
                }

                d.dismiss();
            }
        });
        b.create().show();
    }

    private void updateAnanimal(int databaseId, String animal) {

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(AnimalTable.COLUMN_ANIMAL, animal);

        db.update(
                AnimalTable.TABLE_ANIMALS,
                values,
                AnimalTable.COLUMN_ID + " = ?",
                new String[] {
                        Integer.toString(databaseId)
                }
        );

        updateCursor();
    }

    private void updateCursor() {
        Cursor cursor = helper.getReadableDatabase().query(AnimalTable.TABLE_ANIMALS,
                                                           null,
                                                           selection,
                                                           selectionArgs,
                                                           null,
                                                           null,
                                                           orderBy,
                                                           null);
        adapter.swapCursor(cursor);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.main_sort:
                Log.d("animal", "Option Menu Click SORT");
                sortedList();
                updateCursor();
                return true;
            case R.id.main_add:
                Log.d("animal", "Option Menu Click ADD");
                addAnimalDialog();
                return true;
            case R.id.main_search:
                Log.d("animal", "Option Menu Click SEARCH");

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        int position = info.position;

        switch (item.getItemId()) {
            case R.id.context_select:
                Log.d("animal", "Option CONTEXT Menu Click SELECTET");

                return true;
            case R.id.context_update:
                Log.d("animal", "Option CONTEXT Menu Click UPDATE");
                showUpdateAnimal(position);
                return true;
            case R.id.context_delete:
                Log.d("animal", "Option CONTEXT Menu Click DELETE");
                deleteAnimals(position);
                return true;
        }

        return super.onContextItemSelected(item);
    }
}
