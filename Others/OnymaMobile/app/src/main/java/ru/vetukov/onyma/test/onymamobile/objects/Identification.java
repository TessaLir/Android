package ru.vetukov.onyma.test.onymamobile.objects;

import ru.vetukov.onyma.test.onymamobile.enums.IdentificationType;

class Identification {

    private String mSeria;
    private String mNumber;
    private String mDate;
    private String mGiven;
    private IdentificationType mType;

    public Identification(String pSeria
                         ,String pNumber
                         ,String pDate
                         ,String pGiven
                         ,IdentificationType pType) {

        mSeria  = pSeria;
        mNumber = pNumber;
        mDate   = pDate;
        mGiven  = pGiven;
        mType   = pType;

    }

    @Override
    public String toString() {
        return String.format("%s: %s %s, выдан %s, %s", mType,  mSeria, mNumber, mGiven, mDate);
    }
}
