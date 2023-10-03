package com.example.sample_remainder.data;

import android.provider.BaseColumns;

public final class labTestContract {
    private labTestContract() {}

    public static final class LabTestEntry implements BaseColumns {
        public static final String TABLE_NAME = "lab_test_reminders";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_LAB_TEST_NAME = "lab_test_name";
        public static final String COLUMN_VENUE = "lab_venue";
        public static final String COLUMN_REMINDER_TIME = "reminder_time";
    }
}
