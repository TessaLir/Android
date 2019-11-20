package ru.vetukov.spec.ex_2_intentstarter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEdit;
    private Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEdit = findViewById(R.id.main_et_edit);
        btnEnter = findViewById(R.id.main_btn_enter);

        btnEnter.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String text = etEdit.getText().toString();

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(text));

        /*
        * 1. Позвонить. "tel:****"
        * 2. СМС.
        * 3. Получить контакт из телефонного справочника.
        * 4. Открыть ссылку в браузере. "http://www.google.com"
        * 5. Расширить контент/написать письмо
        * 6. Открыть карту по определенным координатам либо запросу. "geo:55.55,16.33"
        * 7. Сделать снимок камеры
        * ...
        * */

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // обработка собития
        }

    }
}
