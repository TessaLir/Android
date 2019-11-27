package ru.vetukov.spec.ex_4_metrolines.ui;

import android.os.Bundle;
import android.util.SparseArray;
import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import ru.vetukov.spec.ex_4_metrolines.R;
import ru.vetukov.spec.ex_4_metrolines.data.ExpandedLine;
import ru.vetukov.spec.ex_4_metrolines.data.MetroObject;
import ru.vetukov.spec.ex_4_metrolines.data.Station;
import ru.vetukov.spec.ex_4_metrolines.recycler.StationAdapter;

public class MainActivity extends AppCompatActivity {

    private List<MetroObject> objects = new ArrayList<>();
    private List<MetroObject> lines = new ArrayList<>();
    private SparseArray<List<MetroObject>> lineStations = new SparseArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initStations();

        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        // list.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        StationAdapter adapter = new StationAdapter(objects, lineStations);
        recycler.setAdapter(adapter);

    }

    private void initStations() {
        // 1
        addLine(lines, 1, "Сокольническая", 0xFFd92b2c);
        List<MetroObject> lineObjects = new ArrayList<>();

        addStation(lineObjects, 1, "Бульвар Рокоссовского", 0x88d92b2c);
        addStation(lineObjects, 2, "Черкизовская", 0x88d92b2c);
        addStation(lineObjects, 3, "Преображенская площадь", 0x88d92b2c);

        lineStations.put(1, lineObjects);

        // 2
        addLine(lines, 2, "Замоскворецкая", 0xFF4dbe52);
        lineObjects = new ArrayList<>();

        addStation(lineObjects, 11, "Тверская", 0x884dbe52);
        addStation(lineObjects, 12, "Театральная", 0x884dbe52);
        addStation(lineObjects, 13, "Новокузнецкая", 0x884dbe52);

        lineStations.put(2, lineObjects);

        // 3
        addLine(lines, 3, "Арбатско-Покровская", 0xFF367cc7);
        lineObjects = new ArrayList<>();

        addStation(lineObjects, 21, "Парк Победы", 0x88367cc7);
        addStation(lineObjects, 22, "Киевская", 0x88367cc7);
        addStation(lineObjects, 23, "Смоленская", 0x88367cc7);
        addStation(lineObjects, 24, "Арбатская", 0x88367cc7);

        lineStations.put(3, lineObjects);

        // 4
        addLine(lines, 4, "Филёвская", 0xFF4dc6f4);
        lineObjects = new ArrayList<>();

        addStation(lineObjects, 31, "Выставочная", 0x884dc6f4);
        addStation(lineObjects, 32, "Международная", 0x884dc6f4);
        addStation(lineObjects, 33, "Кутузовская", 0x884dc6f4);
        addStation(lineObjects, 34, "Пионерская", 0x884dc6f4);
        addStation(lineObjects, 35, "Кунцевская", 0x884dc6f4);

        lineStations.put(4, lineObjects);

        // populate objects

        for(MetroObject object : lines) {
            objects.add(object);
            objects.addAll(lineStations.get(object.getId()));
        }
    }

    private void addLine(List<MetroObject> objects, int id, String name, @ColorInt int background) {
        objects.add(new ExpandedLine(name, id, background));
    }

    private void addStation(List<MetroObject> objects, int id, String name, @ColorInt int background) {
        objects.add(new Station(name, id, background));
    }

}
