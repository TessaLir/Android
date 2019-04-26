package ru.vetukov.onyma.test.onymamobile.enums;

public enum ContactType {
    TELEPHONE("Телефон", "Telephone"),
    MOBILE("Мобильный", "Mobile"),
    FAX("Факс", "Fax"),
    EMAIL("E-mail", "E-mail");

    private String mRuValue;
    private String mEnValue;

    ContactType(String pRuValue, String pEnValue) {
        mRuValue = pRuValue;
        mEnValue = pEnValue;
    }

    public String getRuValue() {
        return mRuValue;
    }

    public String getEnValue() {
        return mEnValue;
    }

    @Override
    public String toString() {
        return mRuValue;
    }
}
