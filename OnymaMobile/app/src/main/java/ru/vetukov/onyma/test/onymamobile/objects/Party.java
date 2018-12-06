package ru.vetukov.onyma.test.onymamobile.objects;

import java.util.ArrayList;


public class Party {

    private String mPartyID;
    private String mDateOfBirth;
    private String mAddressOfBirth;
    private String mAddress;
    private String mGroupID;
    private String mGroupName;
    private Contract mContract;

    private ArrayList<Contact> mContacts;
    private ArrayList<Identification> mIdentifications;

    public Party() {
        mContract           = new Contract();

        mContacts           = new ArrayList<>();
        mIdentifications    = new ArrayList<>();

    }

    public String getGroupID() {
        return mGroupID;
    }

    public void setGroupID(String groupID) {
        mGroupID = groupID;
    }

    public Contract getContract() {
        return mContract;
    }

    public void setContract(Contract contract) {
        mContract = contract;
    }

    public String getPartyID() {
        return mPartyID;
    }

    public void setPartyID(String partyID) {
        mPartyID = partyID;
    }

    public String getDateOfBirth() {
        return mDateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        mDateOfBirth = dateOfBirth;
    }

    public String getAddressOfBirth() {
        return mAddressOfBirth;
    }

    public void setAddressOfBirth(String addressOfBirth) {
        mAddressOfBirth = addressOfBirth;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public ArrayList<Contact> getContacts() {
        return mContacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        mContacts = contacts;
    }

    public ArrayList<Identification> getIdentifications() {
        return mIdentifications;
    }

    public void setIdentifications(ArrayList<Identification> identifications) {
        mIdentifications = identifications;
    }

    public String getGroupName() {
        return mGroupName;
    }

    public void setGroupName(String groupName) {
        mGroupName = groupName;
    }
}
