package ru.example.ex_021_040_musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle("Your Order");

        Intent intent = getIntent();

        if (intent != null) {

            String userName = intent.getExtras().getString(MainActivity.UNAME_TAG);
            String goods = intent.getExtras().getString(MainActivity.GOODS_TAG);
            int quantity = intent.getExtras().getInt(MainActivity.QUANT_TAG);
            double price = intent.getExtras().getDouble(MainActivity.PRICE_TAG);
            double orderPrice = intent.getExtras().getDouble(MainActivity.ORDPRICE_TAG);

            TextView view = findViewById(R.id.order_tv_result);
            view.setText(String.format( "Customer name: %s\n" +
                                        "Goods name: %s\n" +
                                        "Quantity: %s\n" +
                                        "Price: %s\n" +
                                        "Order price: %s\n",
                                        userName, goods, quantity, price, orderPrice));

        }
    }
}
