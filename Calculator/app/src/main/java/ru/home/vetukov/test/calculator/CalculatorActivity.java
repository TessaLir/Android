package ru.home.vetukov.test.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

public class CalculatorActivity extends AppCompatActivity {

    ArrayList<BottonLine> buttonsLine = new ArrayList<>();
    ButtonAdapter adapter;
    RecyclerView rvCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        getData();

        setContentView(R.layout.activity_calculator);
        rvCalculator = (RecyclerView) findViewById(R.id.bottons_items);

        adapter = new ButtonAdapter(buttonsLine);

        rvCalculator.setLayoutManager(new LinearLayoutManager(this));
//        rvCalculator.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
//        rvCalculator.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvCalculator.setAdapter(adapter);


    }

    //---------------------------------------------------------------------------------------------
    // подумать и отрефакторить эту страсть.
    private void getData() {
        InputSource myMetro = new InputSource(getResources().openRawResource(R.raw.buttons));
        XPath xPath = XPathFactory.newInstance().newXPath();
        String expression = "/bottons/line";
        NodeList nodes;
        List<Botton> bottons = new ArrayList<>();
        BottonLine bottonLine;

        try {
            nodes = (NodeList) xPath.evaluate(expression,
                    myMetro,
                    XPathConstants.NODESET);
            if (nodes != null) {
                int numMetro = nodes.getLength();
                for (int i = 0; i < numMetro; i++) {
                    NodeList lines = nodes.item(i).getChildNodes();
                    for (int j = 0; j < lines.getLength(); j++) {
                        NodeList bottonsXML = lines.item(j).getChildNodes();
                        Botton botton = new Botton();

                        for (int b = 0; b < bottonsXML.getLength(); b++) {
                            String nodeName = bottonsXML.item(b).getNodeName();

                            if (nodeName.equals("name")) {
                                botton.setName(bottonsXML.item(b).getTextContent());
                            }

                            if (nodeName.equals("id")) {
                                botton.setId(Integer.parseInt(bottonsXML.item(b).getTextContent()));
                            }
                        }

                        if (botton.getName() != null)
                            bottons.add(botton);
                    }
                    bottonLine = new BottonLine(bottons.get(0)
                            ,bottons.get(1)
                            ,bottons.get(2)
                            ,bottons.get(3)
                            ,bottons.get(4));
                    buttonsLine.add(bottonLine);
                    bottons.clear();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
