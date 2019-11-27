package ru.vetukov.tmp.ex_4_mymetrolines.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import ru.vetukov.tmp.ex_4_mymetrolines.R;
import ru.vetukov.tmp.ex_4_mymetrolines.ui.data.Line;
import ru.vetukov.tmp.ex_4_mymetrolines.ui.data.Metro;
import ru.vetukov.tmp.ex_4_mymetrolines.ui.data.Station;
import ru.vetukov.tmp.ex_4_mymetrolines.ui.recycler.MetroAdapter;

public class MainActivity extends AppCompatActivity implements MetroAdapter.OnItemClickListener {

    ArrayList<Metro> metroList = new ArrayList<>();
    MetroAdapter adapter;
    RecyclerView rvMetro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();

        setContentView(R.layout.activity_main);
        rvMetro = (RecyclerView) findViewById(R.id.rvMetro);

        adapter = new MetroAdapter(metroList);
        adapter.setOnItemListener(this);

        rvMetro.setLayoutManager(new LinearLayoutManager(this));
        rvMetro.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvMetro.setAdapter(adapter);
    }

    private void toggleSelection(int position) {
        adapter.toggleSelection(position);
        invalidateOptionsMenu();
    }

    @Override
    public void onItemClick(View itemView, int position) {
        toggleSelection(position);
    }

    //---------------------------------------------------------------------------------------------
    // подумать и отрефакторить эту страсть.
    private void getData() {
        InputSource myMetro = new InputSource(getResources().openRawResource(R.raw.metro));
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "/metro/line";
        NodeList nodes;

        try {
            nodes = (NodeList) xPath.evaluate(expression,
                    myMetro,
                    XPathConstants.NODESET);
            if (nodes != null) {
                int numMetro = nodes.getLength();
                for (int i = 0; i < numMetro; i++) {
                    String lineName = "";
                    Line tmpLine = null;
                    NodeList lines = nodes.item(i).getChildNodes();

                    for (int j = 0; j < lines.getLength(); j++) {
                        String nodeName = lines.item(j).getNodeName();

                        if (nodeName.equals("name")) {
                            lineName = lines.item(j).getTextContent();
                            tmpLine = new Line(lineName);
                            metroList.add(tmpLine);
                        }

                        if (nodeName.equals("color")) {
                            if (tmpLine != null) {
                                tmpLine.setColor(lines.item(j).getTextContent());
                            }
                        }
                        else if (nodeName.equals("stations")) {
                            NodeList stations = lines.item(j).getChildNodes();

                            for (int s = 0; s < stations.getLength(); s++) {
                                Node st = stations.item(s);

                                if (st.getNodeType() != Node.TEXT_NODE) {
                                    String station = st.getTextContent();

                                    for (Metro line : metroList) {
                                        if (((Line) line).getName().equals(lineName)) {
                                            ((Line) line).addStation(new Station(station));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
