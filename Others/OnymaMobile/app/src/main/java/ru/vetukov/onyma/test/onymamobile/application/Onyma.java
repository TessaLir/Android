package ru.vetukov.onyma.test.onymamobile.application;

import android.app.Application;
import android.util.Log;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.vetukov.onyma.test.onymamobile.enums.ContactType;
import ru.vetukov.onyma.test.onymamobile.objects.Contact;
import ru.vetukov.onyma.test.onymamobile.objects.Contract;
import ru.vetukov.onyma.test.onymamobile.objects.Login;
import ru.vetukov.onyma.test.onymamobile.objects.User;
import ru.vetukov.onyma.test.onymamobile.query.AuthQuery;
import ru.vetukov.onyma.test.onymamobile.query.BodyHardQuery;
import ru.vetukov.onyma.test.onymamobile.query.BodyLightQuery;
import ru.vetukov.onyma.test.onymamobile.result.ContactsExample;
import ru.vetukov.onyma.test.onymamobile.result.ContractExample;
import ru.vetukov.onyma.test.onymamobile.result.GroupExample;
import ru.vetukov.onyma.test.onymamobile.result.PartyExample;
import ru.vetukov.onyma.test.onymamobile.result.ResultLiteExample;
import ru.vetukov.onyma.test.onymamobile.service.OnymaService;

public class Onyma extends Application {

    // TODO: В перспективе
    public static final String PREF_AUTH_KEY        = "Onyma.PartyActivity.AUTH";
    public static final String PREF_LOGIN_KEY       = "Onyma.PartyActivity.LOGIN";
    public static final String PREF_REALM_KEY       = "Onyma.PartyActivity.REALM";
    public static final String PREF_PASSWORD_KEY    = "Onyma.PartyActivity.PASSWORD";
    public static final String PREF_REMEMBER_KEY    = "Onyma.PartyActivity.REMEMBER";

    // константы для запросов.
    private static final int REQ_AUTH_KEY       = 1000;
    private static final int REQ_REAUTH_KEY     = 1001;
    private static final int REQ_PARTY_KEY      = 1010;

    private static final int REQ_DOB_KEY        = 1030;
    private static final int REQ_PER_ACC_KEY    = 1040;
    private static final int REQ_ADDRESS_ID_KEY = 1050;
    private static final int REQ_ADDRESS_KEY    = 1051;

    private static final int REQ_BALACE_KEY     = 1070;
    private static final int REQ_BONUS_KEY      = 1080;
    private static final int REQ_CONTACTS_KEY   = 1090;


    private static final String TAG = "Onyma.App";
    private static OnymaService service;
    private Retrofit retrofit;

    private Login   mLogin;
    private User    mUser;

    public Onyma(Login pLogin) {

        mLogin  = pLogin;
        mUser   = new User();
        mUser.setLogin(mLogin.getLogin());

        retrofit = new Retrofit.Builder().baseUrl(pLogin.getServer())
                                                 .addConverterFactory(GsonConverterFactory.create())
                                                 .build();

        service = retrofit.create(OnymaService.class);

        getAuthorisition();

    }

    // 00. Авторизация пользователя
    // TODO: Требуется доделать авторизацию!!!
    private void getAuthorisition() {

        // TODO: Проверка авторизованны ли мы по данному ключу,
        // TODO:          если да, то оставляем его, если нет, повторная авторизация

        if (mUser.getAuthKey() == null) {

            HashMap<String, String> result = new HashMap<>();
            result.put("user", mLogin.getLogin());
            result.put("pass", mLogin.getPassword());
            result.put("realm", mLogin.getRealm());

            AuthQuery query = new AuthQuery(result, REQ_AUTH_KEY);

            Call<ResultLiteExample> mCall = service.getPartyAuth(query);

            mCall.enqueue(new MyAsync<ResultLiteExample>());
        }

    }

    public User getUserInfo() {
        // TODO: Тут описать все то, что нужно, для получения информации
        getPartyID();

        while(true) {
            if (mUser.isFinish()) break;
            else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        return mUser;
    }

    // 01. Получить Идентификатор КА
    private void getPartyID() {

        HashMap<String, String> lMap = new HashMap<>();
        lMap.put("p_login", mUser.getLogin());

        BodyLightQuery lq = new BodyLightQuery("onm_api_ttk_api_ttk_lk_get_party_by_login"
                                      ,REQ_PARTY_KEY
                                      ,lMap
                                      ,mUser.getAuthKey());

        Call<ResultLiteExample> call = service.getLightQuery(lq);

        call.enqueue(new MyAsync<ResultLiteExample>());

    }
    // 01. Получить данные по КА
    private void getParty() {

        HashMap<String, HashMap<String, String>> hMap = new HashMap<>();
        HashMap<String, String> lMap = new HashMap<>();

        lMap.put("eq", mUser.getParty().getPartyID());
        hMap.put("party_id", lMap);

        BodyHardQuery hq = new BodyHardQuery("onm_rm_api_rm_party", hMap, mUser.getAuthKey());

        Call<PartyExample> call = service.getParty(hq);

        call.enqueue(new MyAsync<PartyExample>());


    }
    // 02. Получить Договор
    private void getContract() {

        HashMap<String, HashMap<String, String>> hMap = new HashMap<>();
        HashMap<String, String> lMap = new HashMap<>();

        lMap.put("eq", mUser.getParty().getPartyID());
        hMap.put("party_id", lMap);

        BodyHardQuery hq = new BodyHardQuery("onm_rm_api_rm_contract_list", hMap, mUser.getAuthKey());

        Call<ContractExample> call = service.getContract(hq);

        call.enqueue(new MyAsync<ContractExample>());

    }
    // 03. Получить дату рождения
    private void getDOB() {
        getAttrValue(mUser.getParty().getPartyID(), "100420000000000000004", REQ_DOB_KEY);
    }
    // 04. Лицевой счет
    private void getPersonalAccount() {
        getAttrValue(mUser.getParty().getContract().getContractID(), "100420000000000000091", REQ_PER_ACC_KEY);
    }
    // 05. Группу название
    private void getGroupName() {
        HashMap<String, HashMap<String, String>> hMap = new HashMap<>();
        HashMap<String, String> lMap = new HashMap<>();

        lMap.put("eq", mUser.getParty().getGroupID());
        hMap.put("group_id", lMap);

        BodyHardQuery hq = new BodyHardQuery("onm_api_ac_group_graph", hMap, mUser.getAuthKey());

        Call<GroupExample> call = service.getGroupName(hq);

        call.enqueue(new MyAsync<GroupExample>());
    }
    // 06. Адрес Фактический
    private void getAddressID() {
        getAttrValue(mUser.getParty().getPartyID(), "100420000000000000011", REQ_ADDRESS_ID_KEY);
    }
    private void getAddress() {
        HashMap<String, String> lMap = new HashMap<>();
        lMap.put("p_addr_id", mUser.getParty().getAddress());

        BodyLightQuery lq = new BodyLightQuery("onm_rm_api_addr_workflow_print"
                                      ,REQ_ADDRESS_KEY
                                      ,lMap
                                      ,mUser.getAuthKey());

        Call<ResultLiteExample> call = service.getLightQuery(lq);

        call.enqueue(new MyAsync<ResultLiteExample>());
    }
    // 07. Получить Баланс
    private void getBalance() {
        HashMap<String, String> lMap = new HashMap<>();
        lMap.put("p_contract_id", mUser.getParty().getContract().getContractID());

        BodyLightQuery lq = new BodyLightQuery("onm_api_ttk_api_ttk_lk_get_remainder"
                                      ,REQ_BALACE_KEY
                                      ,lMap
                                      ,mUser.getAuthKey());

        Call<ResultLiteExample> call = service.getLightQuery(lq);

        call.enqueue(new MyAsync<ResultLiteExample>());

    }
    // 08. Получить Бонус
    private void getBonus() {
        HashMap<String, String> lMap = new HashMap<>();
        lMap.put("p_contract_id", mUser.getParty().getContract().getContractID());

        BodyLightQuery lq = new BodyLightQuery("onm_api_ttk_api_ttk_lk_bonus_get_bonus_remainder"
                ,REQ_BONUS_KEY
                ,lMap
                ,mUser.getAuthKey());

        Call<ResultLiteExample> call = service.getLightQuery(lq);

        call.enqueue(new MyAsync<ResultLiteExample>());

    }
    // TODO: 09. Контакты
    private void getContacts() {
        HashMap<String, String> lMap = new HashMap<>();

        lMap.put("p_party_id", mUser.getParty().getPartyID());

        BodyLightQuery lq = new BodyLightQuery("onm_api_ttk_api_ttk_lk_get_party_contact"
                                      ,REQ_CONTACTS_KEY
                                      ,lMap
                                      ,mUser.getAuthKey());

        Call<ContactsExample> call = service.getContacts(lq);

        call.enqueue(new MyAsync<ContactsExample>());
    }
    // TODO: 10. Удостоверения личности

    // TODO: возможно оно и не понядобится

    public OnymaService getService() { return service; }

    public User getUser() {
        return mUser;
    }

    private void getAttrValue(String pObject, String pAttr, int id) {
        HashMap<String, String> lMap = new HashMap<>();
        lMap.put("pobj_id", pObject);
        lMap.put("pattr", pAttr);

        BodyLightQuery lq = new BodyLightQuery("onm_api_db_attr_get_attr_value_str"
                                      ,id
                                      ,lMap
                                      ,mUser.getAuthKey());

        Call<ResultLiteExample> call = service.getLightQuery(lq);

        call.enqueue(new MyAsync<ResultLiteExample>());
    }

    class MyAsync<T> implements Callback<T> {


        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            T example = response.body();

            String msg = "";

            // 01. Контрагент
            if (example instanceof PartyExample)    changeParty((PartyExample) example);
            // 02. Договор
            if (example instanceof ContractExample) changeContract((ContractExample) example);
            // 05. Группу название
            if (example instanceof GroupExample)    changeGroupName((GroupExample) example);
            // TODO: 09. Контакты
            if (example instanceof ContactsExample) changeContacts((ContactsExample) example);



            if (example instanceof ResultLiteExample) {
                String result = ((ResultLiteExample) example).getResult().getReturn();
                int id = ((ResultLiteExample) example).getId();


                // 00.1 Авторизация PartyID
                if (id == REQ_PARTY_KEY) {
                    mUser.getParty().setPartyID(result);
                    getParty();
                }
                // 00.2 Авторизация Party Object
                if (id == REQ_AUTH_KEY) {
                    if (result != null) {
                        mUser.setAuthKey(result);
                        msg = "Ключ: " + mUser.getAuthKey();
                    } else {
                        msg = "ERROR: " + ((ResultLiteExample) example).getError().getMessage();
                        mUser.setErrorMsg(msg);
                    }

                }
                // 03. Дата рождения
                if (id == REQ_DOB_KEY) {
                    mUser.getParty().setDateOfBirth(result);
                    getPersonalAccount();
                }
                // 04. Лицевой счет
                if (id == REQ_PER_ACC_KEY) {
                    mUser.getParty().getContract().setPersAcc(result);
                    getGroupName();
                }
                // 06. Адрес Фактический
                if (id == REQ_ADDRESS_ID_KEY) {
                    mUser.getParty().setAddress(result);
                    getAddress();
                }
                if (id == REQ_ADDRESS_KEY) {
                    mUser.getParty().setAddress(result.replace(" ул.", "\nул.")
                                                      .replace(" д.", "\nд."));
                    getBalance();
                }

                // 07. Получить Баланс
                if (id == REQ_BALACE_KEY) {
                    mUser.getParty().getContract().setBalance(result);
                    getBonus();
                }
                // 08. Получить Бонус
                if (id == REQ_BONUS_KEY) {
                    mUser.getParty().getContract().setBonus(result);
                    getContacts();
                }
                // TODO: 10. Удостоверения личности


                Log.d(TAG, msg);

            }

            mUser.upProcessCountRun();
        }
        @Override
        public void onFailure(Call<T> call, Throwable t) {
            // TODO: Обработать исключение, если ответ от вервера не пришел
            mUser.upProcessCountRun();
        }

    }


    private void changeContacts(ContactsExample example) {
        if(example.getResult() != null) {

            List<ContactsExample.Return> result = example.getResult().getReturn();

            for (ContactsExample.Return el : result) {
                // TODO: Поправить значение получения типа
                Contact contact = new Contact(el.getValue(), ContactType.TELEPHONE);

                mUser.getParty().getContacts().add(contact);
            }


            for (int i = 0; i < 15; i++) mUser.getParty().getContacts().add(new Contact("TEST", ContactType.TELEPHONE));
        }

        mUser.setFinish();
    }

    private void changeGroupName(GroupExample example) {
        if(example.getResult() != null) {
            GroupExample.Return result = example.getResult().getReturn().get(0);
            mUser.getParty().setAddressOfBirth(result.getNameInt());
        }

        getAddressID();
    }

    private void changeParty(PartyExample example) {
        if (example.getResult() != null) {
            PartyExample.Return result = example.getResult().getReturn().get(0);

            String[] fio = result.getNameAttr().split(" ");
            if (fio.length == 3) {
                mUser.setLname(fio[0]);
                mUser.setFName(fio[1]);
                mUser.setPatronymic(fio[2]);
            }
            else mUser.setFName(result.getNameAttr());

            mUser.getParty().setGroupID(result.getBaseGroupId());

            getContract();

        }
    }

    private void changeContract(ContractExample example) {
        if (example.getResult() != null) {
            ContractExample.Return result = example.getResult().getReturn().get(0);

            Contract contract = mUser.getParty().getContract();
            contract.setContractID(result.getContractId());
            contract.setDateEnd(result.getEndDt());
            contract.setDateStart(result.getStartDt());
            contract.setDogcode(result.getContractNum());

            getDOB();
        }
        // TODO: Можно обработать если что то пошло не так.
    }
}
