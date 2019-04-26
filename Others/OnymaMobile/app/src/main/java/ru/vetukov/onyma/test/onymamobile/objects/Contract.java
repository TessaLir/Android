package ru.vetukov.onyma.test.onymamobile.objects;

public class Contract {

    private String mContractID;
    private String mDogcode;
    private String mPersAcc;

    private String mBalance;
    private String mBonus;

    private String mDateStart;
    private String mDateEnd;

    public Contract() {}

    public String getContractID() {
        return mContractID;
    }

    public void setContractID(String contractID) {
        mContractID = contractID;
    }

    public String getDogcode() {
        return mDogcode;
    }

    public void setDogcode(String dogcode) {
        mDogcode = dogcode;
    }

    public String getPersAcc() {
        return mPersAcc;
    }

    public void setPersAcc(String persAcc) {
        mPersAcc = persAcc;
    }

    public String getDateStart() {
        return mDateStart;
    }

    public void setDateStart(String dateStart) {
        mDateStart = dateStart;
    }

    public String getDateEnd() {
        return mDateEnd;
    }

    public void setDateEnd(String dateEnd) {
        mDateEnd = dateEnd;
    }

    public String getBalance() {
        return mBalance;
    }

    public void setBalance(String balance) {
        mBalance = balance;
    }

    public String getBonus() {
        return mBonus;
    }

    public void setBonus(String bonus) {
        mBonus = bonus;
    }
}
