package ru.vetukov.onyma.test.onymamobile.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class Login implements Parcelable {

    private String  mLogin;
    private String  mPassword;
    private String  mServer;
    private String  mRealm;

    public Login(String login, String password, String server, String realm) {
        mLogin      = login;
        mPassword   = password;
        mServer     = server;
        mRealm      = realm;
    }

    public Login(Parcel in) {
        String[] data = new String[4];
        in.readStringArray(data);
        mLogin      = data[0];
        mPassword   = data[1];
        mServer     = data[2];
        mRealm      = data[3];
    }

    public String getLogin() {
        return mLogin;
    }

    public String getPassword() {
        return mPassword;
    }

    public String getServer() {
        return mServer;
    }

    public String getRealm() {
        return mRealm;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {mLogin, mPassword, mServer, mRealm});
    }

    public static final Parcelable.Creator<Login> CREATOR = new Parcelable.Creator<Login>() {

        @Override
        public Login createFromParcel(Parcel source) {
            return new Login(source);
        }

        @Override
        public Login[] newArray(int size) {
            return new Login[size];
        }
    };
}
