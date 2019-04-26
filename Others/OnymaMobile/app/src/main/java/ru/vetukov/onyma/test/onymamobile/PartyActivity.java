package ru.vetukov.onyma.test.onymamobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import ru.vetukov.onyma.test.onymamobile.adapters.ContactsRecyclerViewAdapter;
import ru.vetukov.onyma.test.onymamobile.application.Onyma;
import ru.vetukov.onyma.test.onymamobile.objects.Login;
import ru.vetukov.onyma.test.onymamobile.objects.User;


public class PartyActivity extends AppCompatActivity {

    public static final String LOG              = "Onyma.PartyActivity";

    private User            mUser;
    private Onyma           mOnyma;
    private Intent          mIntent;

    private RecyclerView    mRecyclerView;
    private ProgressBar     mPBar;
    private TextView        mFIO;
    private TextView        mDOB;
    private TextView        mCity;
    private TextView        mAddress;
    private TextView        mDogcode;
    private TextView        mPAcc;
    private TextView        mBalance;
    private TextView        mBonus;
    private TextView        mError;
    private View            mPartyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party);

        mRecyclerView   = findViewById(R.id.client_contacts);
        mError          = findViewById(R.id.text_view_error);
        mPBar           = findViewById(R.id.progress_view);
        mPartyView      = findViewById(R.id.view_party);
        mFIO            = findViewById(R.id.client_fio);
        mDOB            = findViewById(R.id.client_dob);
        mCity           = findViewById(R.id.client_address_berth);
        mAddress        = findViewById(R.id.client_address);
        mDogcode        = findViewById(R.id.client_dogcode);
        mPAcc           = findViewById(R.id.client_personal_account);
        mBalance        = findViewById(R.id.client_balance);
        mBonus          = findViewById(R.id.client_bonus);

        mIntent         = getIntent();

        new RunAuth().execute();
    }

    class RunAuth extends AsyncTask<Void, String, Integer> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // запускаем соединение с сервером. Получаем Ключ авторизации.
            mOnyma = new Onyma((Login) mIntent.getParcelableExtra(MainActivity.LOGIN_KEY));
        }
        @Override
        protected Integer doInBackground(Void... voids) {
            // Ждем ответ от сервера, положительный или отрицательный.
            while (true) {
                // Получаем объект Авторизации, смотрим авторизованно, или нет.
                // TODO: В место Ключа надоп роверить код позиции
                if (mOnyma.getUser().getAuthKey() != null
                   || mOnyma.getUser().getErrorMsg() != null) break;
            }
            // Если мы ошиблись в Логине и пароле то не смотрим, что бедет дальше.
            // TODO: тут может быть ошибка, что нет связи,а не только "Не верный логин и пароль"
            if (mOnyma.getUser().getAuthKey() == null) {
                publishProgress(mOnyma.getUser().getErrorMsg());
            }
            else {
                mUser = mOnyma.getUserInfo();
                Log.d(LOG, "Пользователь авторизован: " + mUser.getLogin());
                // Комманда поиска нужно информации по Контрагенту.
                while(true) {
                    if (mUser.isFinish()) {
                        break;
                    } else {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                // TODO: Тут мы и смотрим, что у нас с полученным и собранным контактом. и производим с ним работу.
            }

            publishProgress("Все работает!");

            try {
                Thread.sleep(2500);
            }  catch (InterruptedException e) {
                e.printStackTrace();
            }

            return mOnyma.getUser().mProcessCountRun;
        }

        @Override
        protected void onProgressUpdate(String... message) {
            super.onProgressUpdate(message);

            if (!mUser.isFinish()) {

                mPBar.setVisibility(View.INVISIBLE);
                mError.setVisibility(View.VISIBLE);

                mError.setText(message[0]);
            } else {

                mFIO.setText(String.format("%s %s %s"
                                          ,mUser.getLname()
                                          ,mUser.getFName()
                                          ,mUser.getPatronymic()));
                mDOB.setText(mUser.getParty().getDateOfBirth().substring(0, 10));
                mCity.setText(mUser.getParty().getAddressOfBirth());
                mAddress.setText(mUser.getParty().getAddress());
                mDogcode.setText(mUser.getParty().getContract().getDogcode());
                mPAcc.setText(mUser.getParty().getContract().getPersAcc());
                mBalance.setText(mUser.getParty().getContract().getBalance());
                mBonus.setText(mUser.getParty().getContract().getBonus());

                ContactsRecyclerViewAdapter adapter = new ContactsRecyclerViewAdapter(mUser.getParty().getContacts());
                LinearLayoutManager llm = new LinearLayoutManager(PartyActivity.this);
                RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();

                mRecyclerView.setAdapter(adapter);
                mRecyclerView.setHasFixedSize(true);
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(llm);
                mRecyclerView.setItemAnimator(itemAnimator);

                mPBar.setVisibility(View.INVISIBLE);
                mPartyView.setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected void onPostExecute(Integer number) {
            super.onPostExecute(number);

            if (number == -1 ) onBackPressed();
        }
    }
}
