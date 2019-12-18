package ru.example.ex_021_040_musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] addresses = {"finik001@gmail.com", "vetukov@stecksoft.ru"};
    private String subject = "Order from Cats shop";

    private Button btnSendMessage;
    private TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        setTitle("Your Order");

        btnSendMessage = findViewById(R.id.order_btn_send_message);
        Intent intent = getIntent();

        if (intent != null) {

            String userName = intent.getExtras().getString(MainActivity.UNAME_TAG);
            String goods = intent.getExtras().getString(MainActivity.GOODS_TAG);
            int quantity = intent.getExtras().getInt(MainActivity.QUANT_TAG);
            double price = intent.getExtras().getDouble(MainActivity.PRICE_TAG);
            double orderPrice = intent.getExtras().getDouble(MainActivity.ORDPRICE_TAG);

            view = findViewById(R.id.order_tv_result);
            view.setText(String.format( "Customer name: %s\n" +
                                        "Goods name: %s\n" +
                                        "Quantity: %s\n" +
                                        "Price: %s\n" +
                                        "Order price: %s\n",
                                        userName, goods, quantity, price, orderPrice));

            btnSendMessage.setOnClickListener(this);

        }
    }

    @Override
    public void onClick(View v) {
        composeEmail(addresses, subject);
    }

    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, view.getText().toString());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


}
