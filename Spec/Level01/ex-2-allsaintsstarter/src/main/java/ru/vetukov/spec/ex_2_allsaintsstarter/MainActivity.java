package ru.vetukov.spec.ex_2_allsaintsstarter;

import androidx.annotation.NonNull;
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

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

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

        }

        adapter = new SaintAdapter(this, R.layout.listviewitem, saints);
        lvList.setAdapter(adapter);

        lvList.setOnItemClickListener(this);

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

        ///

        return super.onContextItemSelected(item);
    }

    // Вызывается при создании меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
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
        Saint saint = saints.get(position);

        Intent intent = new Intent(this, SaintDetail.class);

        intent.putExtra(SAINT_ID, position);
        intent.putExtra(SAINT_NAME, saint.getName());
        intent.putExtra(SAINT_RATING, saint.getRating());

        startActivity(intent);
    }
}
