package ru.vetukov.onyma.test.onymamobile.enums;

public enum IdentificationType {
    PASPORT("Паспорт", "PASPORT");

    private String mRuName;
    private String mEnName;

    IdentificationType(String ruName, String enName) {
        mRuName = ruName;
        mEnName = enName;
    }

    public String getRuName() {
        return mRuName;
    }

    public String getEnName() {
        return mEnName;
    }

    @Override
    public String toString() {
        return mRuName;
    }
}
