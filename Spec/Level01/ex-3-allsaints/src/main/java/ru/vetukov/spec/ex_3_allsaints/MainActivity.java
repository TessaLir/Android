package ru.vetukov.spec.ex_3_allsaints;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    public static final String SAINT_ID = "SAINT_ID";
    public static final String SAINT_NAME = "SAINT_NAME";
    public static final String SAINT_RATING = "SAINT_RATING";

    public static final int RATING_REQUEST = 234;

    private ListView lvList;

    List<Saint> saints = new ArrayList<>();
    SaintAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvList = findViewById(R.id.main_list);

        // Источник данных для парсера XML из ресурсов
        InputSource mySaints = new InputSource(getResources().openRawResource(R.raw.saints));
        // Новый XPath запрос
        XPath xPath = XPathFactory.newInstance().newXPath();

        // Подробно об XPath
        // http://www.w3schools.com/xsl/xpath_syntax.asp

        // запрос
        String expression = "/saints/saint";

        NodeList nodes;
        try {
            // Результат XPath запроса - набор узлов
            nodes = (NodeList) xPath.evaluate(expression, mySaints, XPathConstants.NODESET);
            if (nodes != null) {
                int numSaints = nodes.getLength();

                // для каждого из узлов
                for (int i = 0; i < numSaints; i++) {
                    // Узел
                    Node saint = nodes.item(i);
                    String name = saint.getFirstChild().getTextContent();

                    Log.d("##$$%%", "name: " + name);

                    saints.add(new Saint(name, "", "", 0f));
                }
            }
        } catch (Exception e) {
            ///
        }

        adapter = new SaintAdapter(this, R.layout.listviewitem, saints);
        lvList.setAdapter(adapter);

        lvList.setOnItemClickListener(this);
        lvList.setOnItemLongClickListener(this);

    }

    // Вызывается при создании контекстного меню
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    // Вызывается при выборе элемента контекстного меню
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        return super.onContextItemSelected(item);
    }

    // Вызывается при создании меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!adapter.hasSelected()) {
            getMenuInflater().inflate(R.menu.main, menu);
        } else {
            getMenuInflater().inflate(R.menu.delete, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    // Вызывается при выборе элемента меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_up:
                Collections.sort(saints);
                adapter.notifyDataSetChanged();
                return true;
            case R.id.menu_down:
                Collections.sort(saints, Collections.<Saint>reverseOrder());
                adapter.notifyDataSetChanged();
                return true;
            case R.id.menu_add:
                showAddDialog();
                return true;
            case R.id.main_delete:
                adapter.deleteSelected();
                invalidateOptionsMenu();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAddDialog() {

        final View dialog = getLayoutInflater().inflate(R.layout.dialog_add, null);

        final EditText name = (EditText) dialog.findViewById(R.id.main_dialog_add);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setView(dialog).setTitle("Add a Saint!");

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String saint = name.getText().toString();
                saints.add(
                        new Saint(saint, "", "", 0f)
                );
                adapter.notifyDataSetChanged();
                dialogInterface.dismiss();
            }
        });

        builder.create().show();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (!adapter.hasSelected()) {
            Saint saint = saints.get(position);

            Intent intent = new Intent(this, SaintDetail.class);

            intent.putExtra(SAINT_ID, position);
            intent.putExtra(SAINT_NAME, saint.getName());
            intent.putExtra(SAINT_RATING, saint.getRating());

            startActivityForResult(intent, RATING_REQUEST);
        } else {
            toggleSelection(position);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == RATING_REQUEST)
        {
            int id = -1;
            float rating = -1f;

            // получить из intent id
            id = data.getIntExtra(SAINT_ID, -1);
            // получить из intent rating
            rating  = data.getFloatExtra(SAINT_RATING, -1f);

            // если id и rating >= 0 то обновить святого , "дернуть" adapter
            if(id >= 0 && rating >= 0)
            {
                saints.get(id).setRating(rating);
                adapter.notifyDataSetChanged();
            }

            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        toggleSelection(position);
        return true;
    }

    private void toggleSelection(int position) {
        adapter.toggleSelection(position);
        invalidateOptionsMenu();
    }

}

