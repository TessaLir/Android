package ru.vetukov.onyma.test.onymamobile;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import ru.vetukov.onyma.test.onymamobile.objects.Login;

public class MainActivity extends AppCompatActivity {

    public static final String LOG          = "Onyma.MainActivity";
    public static final String LOGIN_KEY    = "Onyma.MainActivity.LOGIN";

    private EditText mEditLogin;
    private EditText mEditPassword;
    private EditText mEditServer;
    private Spinner  mSpinnerRealm;
    private Button   mButtonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        mEditLogin      = findViewById(R.id.edit_login);
        mEditPassword   = findViewById(R.id.edit_password);
        mEditServer     = findViewById(R.id.edit_server);
        mSpinnerRealm   = findViewById(R.id.spinner_realm);
        mButtonStart    = findViewById(R.id.button_start);

        mButtonStart.setOnClickListener(onClick());

        setVisibleButton();
        changeText();

    }
    // получить введенные данные из EditText и совершить подключение к сервису.
    private void startConnect() {
        Login mLogin = new Login(mEditLogin.getText().toString()
                ,mEditPassword.getText().toString()
                ,mEditServer.getText().toString()
                ,getRealm());
        Log.d(LOG,  mLogin.getLogin() + " " + mLogin.getRealm());

        // TODO: Добавить создание подключения
        // ...

        Intent intent = new Intent(this, PartyActivity.class);
        intent.putExtra(LOGIN_KEY, mLogin);
        startActivity(intent);

    }
    // Нажатие на кнопку
    private View.OnClickListener onClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Проверяем, все ли поля заполнены, если нет, то выводим соответствующее сообщение.
                switch(isEmptyFields()) {
                    case 1 :
                        showTextMessage("Поля логин, пароль и сервер не могут быть пустыми.");
                        break;
                    case 2 :
                        showTextMessage("Поля логин и пароль не могут быть пустыми.");
                        break;
                    case 3 :
                        showTextMessage("Поле логин не могут быть пустым.");
                        break;
                    case 4 :
                        showTextMessage("Поле пароль не могут быть пустым.");
                        break;
                    case 5 :
                        showTextMessage("Поле сервер не могут быть пустым.");
                        break;
                    default :
                        startConnect();
                        break;
                }
            }
        };
    }
    // Получить Текстовое значение из Spinner'a для значения REALM
    private String getRealm() {
        switch(mSpinnerRealm.getSelectedItemPosition()) {
            case 1 :
                return "ttk";
            default :
                return "onyma.team";
        }
    }
    // Вывести тестовое сообщение на экран
    // TODO: исправить Toast на свою форму.
    private void showTextMessage(String pMessage) {
        Log.d(LOG, String.format("%s - %s", LOG, pMessage));
        // ...
        Toast.makeText(MainActivity.this
                ,pMessage
                ,Toast.LENGTH_SHORT).show();
    }
    // ---------------------------------------------------------------------------
    // Видимость кнопки
    // Зависимость видимости кномки от значений в EditText
    private void setVisibleButton() {
        if (isEmptyFields() == 0) mButtonStart.setVisibility(View.VISIBLE);
        else mButtonStart.setVisibility(View.INVISIBLE);
    }
    // Проверить EditText на заполненость.
    private int isEmptyFields() {
        int lLL = mEditLogin.getText().toString().length();
        int lPL = mEditPassword.getText().toString().length();
        int lSL = mEditServer.getText().toString().length();

        if (lLL == 0 && lPL == 0 && lSL == 0) return 1; // Не запонены Login, Password и Server
        if (lLL == 0 && lPL == 0) return 2; // Не заплнено Login и Password
        if (lLL == 0) return 3; // Не заполнено полк EditLogin
        if (lPL == 0) return 4; // Не заполнено поле EditPassword
        if (lSL == 0) return 5; // Не заполнено поле EditServer

        return 0; // Все поля заполнены, все хорошо.
    }
    // зменить Видимость кнопки, в зависимости от того, введен ли текст в EditText
    private void changeText() {
        mEditLogin.addTextChangedListener(getWatcher());
        mEditPassword.addTextChangedListener(getWatcher());
        mEditServer.addTextChangedListener(getWatcher());
    }
    // Обработка событий для EditText одинаковая. пожтому выведено в отдельный метод.
    @NonNull
    private TextWatcher getWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setVisibleButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
    // ---------------------------------------------------------------------------
}