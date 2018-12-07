package ru.vetukov.test.cathouse.data;

import android.provider.BaseColumns;

public final class HotelContract {

    private HotelContract() {}

    public static final class GuestEntry implements BaseColumns {

        public final static String TABLE_NAME       = "guests";

        public static final String _ID              = BaseColumns._ID;
        public static final String COLUMN_NAME      = "name";
        public static final String COLUMN_CITY      = "city";
        public static final String COLUMN_GENDER    = "gender";
        public static final String COLUMN_AGE       = "age";

        public static final int GENDER_FAMALE       = 0;
        public static final int GENDER_MALE         = 1;
        public static final int GENDER_UNKNOWN      = 2;

    }
}
