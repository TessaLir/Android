package ru.example.ex_021_040_musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener,
                   AdapterView.OnItemSelectedListener {

    private static Button btnAdd;
    private static Button btnMinus;
    private static Spinner spnAnimals;
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

        btnAdd = findViewById(R.id.main_btn_count_add);
        btnMinus = findViewById(R.id.main_btn_count_minus);
        tvItemCount = findViewById(R.id.main_tv_count);
        spnAnimals = findViewById(R.id.main_spn_animals);
        tvPrice = findViewById(R.id.main_tv_price);
        ivItemImage = findViewById(R.id.main_iv_item);

        spinnerArrayList = new ArrayList<>();
        spinnerArrayList.add("Cats");
        spinnerArrayList.add("Dogs");
        spinnerArrayList.add("Rodents");

        animalPrice = new HashMap<>();
        animalPrice.put("Cats", 1500.0);
        animalPrice.put("Dogs", 1000.0);
        animalPrice.put("Rodents", 500.0);

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnAnimals.setAdapter(spinnerAdapter);

        btnAdd.setOnClickListener(this);
        btnMinus.setOnClickListener(this);

        spnAnimals.setOnItemSelectedListener(this);

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
        }
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
