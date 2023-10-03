package com.example.sample_remainder.data;

import android.provider.BaseColumns;

public final class UserContract {
    private UserContract() {}

    public static final class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user_credentials";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
    }
}

