package ru.example.ex_021_040_musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
                          implements View.OnClickListener,
                                     AdapterView.OnItemSelectedListener {

    public static final String UNAME_TAG = "UNAME_TAG";
    public static final String GOODS_TAG = "GOODS_TAG";
    public static final String QUANT_TAG = "QUANT_TAG";
    public static final String PRICE_TAG = "PRICE_TAG";
    public static final String ORDPRICE_TAG = "ORDPRICE_TAG";

    private static Button btnAdd;
    private static Button btnMinus;
    private static Button btnAddOrder;
    private static Spinner spnAnimals;
    private static EditText etUserName;
    private static TextView tvItemCount;
    private static TextView tvPrice;
    private static ImageView ivItemImage;

    private static ArrayList<String> spinnerArrayList;
    private static HashMap animalPrice;

    private ArrayAdapter spinnerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        createSpinner();
        createMap();

        btnAdd.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnAddOrder.setOnClickListener(this);
        spnAnimals.setOnItemSelectedListener(this);

    }

    private void init() {
        btnAdd = findViewById(R.id.main_btn_count_add);
        btnMinus = findViewById(R.id.main_btn_count_minus);
        btnAddOrder = findViewById(R.id.main_btn_add_order);
        etUserName = findViewById(R.id.main_et_user_name);
        tvItemCount = findViewById(R.id.main_tv_count);
        tvPrice = findViewById(R.id.main_tv_price);
        spnAnimals = findViewById(R.id.main_spn_animals);
        ivItemImage = findViewById(R.id.main_iv_item);
    }

    private void createMap() {
        animalPrice = new HashMap<>();
        animalPrice.put("Cats", 1500.0);
        animalPrice.put("Dogs", 1000.0);
        animalPrice.put("Rodents", 500.0);
    }

    private void createSpinner() {
        spinnerArrayList = new ArrayList<>();
        spinnerArrayList.add("Cats");
        spinnerArrayList.add("Dogs");
        spinnerArrayList.add("Rodents");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnAnimals.setAdapter(spinnerAdapter);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.main_btn_count_add :
                setItemCount(1);
                break;
            case R.id.main_btn_count_minus :
                setItemCount(0);
                break;
            case R.id.main_btn_add_order :
                addToCart();
                break;
        }
    }

    private void addToCart() {
        Order order = new Order();

        order.setUserName(etUserName.getText().toString());
        order.setGoodsName(spnAnimals.getSelectedItem().toString());
        order.setQuantity(Integer.parseInt(tvItemCount.getText().toString()));
        order.setOrderPrice(Double.parseDouble(tvPrice.getText().toString()));
        order.setPrice(Double.parseDouble(animalPrice.get(spnAnimals.getSelectedItem().toString()).toString()));

        Log.d("HAPPY", String.format("%s, %s, %s, %s",
                order.getUserName(), order.getGoodsName(), order.getQuantity(), order.getOrderPrice()));

        Intent intent = new Intent(MainActivity.this, OrderActivity.class);

        intent.putExtra(UNAME_TAG, order.getUserName());
        intent.putExtra(GOODS_TAG, order.getGoodsName());
        intent.putExtra(QUANT_TAG, order.getQuantity());
        intent.putExtra(PRICE_TAG, order.getOrderPrice());
        intent.putExtra(ORDPRICE_TAG, order.getPrice());

        startActivity(intent);
    }


    private static void setItemCount(int flag) {
        int count = Integer.parseInt(tvItemCount.getText().toString());
        if (flag == 1) {
            tvItemCount.setText(String.format("%s",++count));
            btnMinus.setClickable(true);
            changePrice();
        } else {
            if (count > 0) {
                tvItemCount.setText(String.format("%s",--count));
                changePrice();
            } else {
                btnMinus.setClickable(false);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        changePrice();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Do somthing
    }

    private static void changePrice() {
        String animal = spnAnimals.getSelectedItem().toString();
        double price = (double) animalPrice.get(animal);
        int count = Integer.parseInt(tvItemCount.getText().toString());

        switch (animal) {
            case "Cats" :
                ivItemImage.setImageResource(R.drawable.cats);
                break;
            case "Dogs" :
                ivItemImage.setImageResource(R.drawable.dogs);
                break;
            case "Rodents" :
                ivItemImage.setImageResource(R.drawable.rodents);
                break;
        }

        tvPrice.setText(String.format("%s", price * count));
    }
}
