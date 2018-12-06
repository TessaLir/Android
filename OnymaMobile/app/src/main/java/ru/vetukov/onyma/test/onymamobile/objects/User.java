package ru.vetukov.onyma.test.onymamobile.objects;

public class User {

    // пройдено этапов сбора информации
    public int mProcessCountRun = 0;

    private String mFName;
    private String mLname;
    private String mPatronymic;
    private String mUserID;
    private String mLogin;
    private String mAuthKey;

    private String mErrorMsg;

    private boolean isFinish;

    private Party  mParty;


    public User() {
        isFinish = false;
        mParty = new Party();

    }

    public int getProcessCountRun() {
        return mProcessCountRun;
    }

    public void upProcessCountRun() {
        mProcessCountRun++;
    }

    public void incorrectAuth() {
        mProcessCountRun = -1;
    }

    public String getFName() {
        return mFName;
    }

    public void setFName(String FName) {
        mFName = FName;
    }

    public String getLname() {
        return mLname;
    }

    public void setLname(String lname) {
        mLname = lname;
    }

    public String getPatronymic() {
        return mPatronymic;
    }

    public void setPatronymic(String patronymic) {
        mPatronymic = patronymic;
    }

    public String getUserID() {
        return mUserID;
    }

    public void setUserID(String userID) {
        mUserID = userID;
    }

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String login) {
        mLogin = login;
    }

    public Party getParty() {
        return mParty;
    }

    public void setParty(Party party) {
        mParty = party;
    }

    public void setProcessCountRun(int processCountRun) {
        mProcessCountRun = processCountRun;
    }

    public String getAuthKey() {
        return mAuthKey;
    }

    public void setAuthKey(String authKey) {
        mAuthKey = authKey;
    }

    public String getErrorMsg() {
        return mErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        mErrorMsg = errorMsg;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish() {
        isFinish = true;
    }
}
