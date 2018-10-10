package ru.vetukov.home.myyandextranslate;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Callback<Translater> {

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://translate.yandex.net")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private TranslateService service = retrofit.create(TranslateService.class);

    private Button button;
    private EditText editText;
    private TextView yandex;
    private TextView text;

    private RadioButton rbRus;
    private RadioButton rbEng;

    private String lang = "en-ru";


    //TODO При развороте приложения, приложение присваивает lang = en-ru нужно поправить!!!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button  = findViewById(R.id.button);
        editText = findViewById(R.id.edit_text);
        text = findViewById(R.id.text);
        yandex = findViewById(R.id.yandex);

        rbRus = findViewById(R.id.rb_rus);
        rbEng = findViewById(R.id.rb_en);

        button.setOnClickListener(this);

        rbEng.setOnClickListener(this);
        rbRus.setOnClickListener(this);

        yandex.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button :
                changeText();
                break;
            case R.id.rb_en :
                lang = "ru-en";
                break;
            case R.id.rb_rus :
                lang = "en-ru";
                break;
            case R.id.yandex :
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://translate.yandex.ru/"));
                startActivity(i);
                break;
        }
    }

    private void changeText() {
        String msg = editText.getText().toString();

        String key = "";

        if (key.length() == 0) text.setText("Не введен ключ разработчика!!!");
        else {
            Call<Translater> call = service.search(key,     //TODO А тут надо вставить свой ключик ;)
                                                   msg,
                                                   lang);
            call.enqueue(this);
            yandex.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResponse(Call<Translater> call, Response<Translater> response) {
        Translater transl = response.body();

        if (transl !=  null) {
            text.setText(transl.getText().toString());
        }
    }

    @Override
    public void onFailure(Call<Translater> call, Throwable t) {
        text.setText("Что то пошло не так. Увы...");
    }
}
