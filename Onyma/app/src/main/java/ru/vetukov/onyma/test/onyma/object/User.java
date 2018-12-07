package ru.vetukov.onyma.test.onyma.object;

public class User {

    private String mLogin;
    private String mPassword;
    private String mServer;
    private String mRealm;
    private String mAuth;
    private String mUserID;

    public User(String login, String password, String server, String realm) {
        mLogin = login;
        mPassword = password;
        mServer = server;
        mRealm = realm;
    }

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String login) {
        mLogin = login;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getServer() {
        return mServer;
    }

    public void setServer(String server) {
        mServer = server;
    }

    public String getRealm() {
        return mRealm;
    }

    public void setRealm(String realm) {
        mRealm = realm;
    }

    public String getAuth() {
        return mAuth;
    }

    public void setAuth(String auth) {
        mAuth = auth;
    }

    public String getUserID() {
        return mUserID;
    }

    public void setUserID(String userID) {
        mUserID = userID;
    }

}
