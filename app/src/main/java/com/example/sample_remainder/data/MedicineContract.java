package com.example.sample_remainder.data;

import android.provider.BaseColumns;

public final class MedicineContract {
    private MedicineContract() {}

    public static final class MedicineEntry implements BaseColumns {
        public static final String TABLE_NAME = "medicine_reminders";
        public static final String COLUMN_ID = "_id"; // Add this line for "_id" column
        public static final String COLUMN_MEDICINE_NAME = "medicine_name";
        public static final String COLUMN_REMINDER_TIME = "reminder_time";
    }

}


