package com.example.sample_remainder.data;

import android.provider.BaseColumns;

public final class HealthCheckContract {
    private HealthCheckContract() {}

    public static final class HealthCheckEntry implements BaseColumns {
        public static final String TABLE_NAME = "health_checkup_reminders";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_USER_ID = "user_id"; // Reference to the user
        public static final String COLUMN_HEALTH_CHECK_NAME = "health_check_name";
        public static final String COLUMN_REMINDER_TIME = "reminder_time";
    }
}

