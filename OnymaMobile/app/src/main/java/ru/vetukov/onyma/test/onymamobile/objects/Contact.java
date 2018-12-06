package ru.vetukov.onyma.test.onymamobile.objects;

import ru.vetukov.onyma.test.onymamobile.enums.*;

public class Contact {

    private String mValue;
    private ContactType mContactType;

    public Contact(String value, ContactType contactType) {
        mValue = value;
        mContactType = contactType;
    }

    public ContactType getContactType() {
        return mContactType;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }
}
