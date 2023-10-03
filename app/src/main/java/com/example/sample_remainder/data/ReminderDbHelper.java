package com.example.sample_remainder.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ReminderDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "reminders.db";
    private static final int DATABASE_VERSION = 1;

    public ReminderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Add this inside your ReminderDbHelper class, inside the onCreate method
        final String SQL_CREATE_USER_TABLE = "CREATE TABLE " +
                UserContract.UserEntry.TABLE_NAME + " (" +
                UserContract.UserEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                UserContract.UserEntry.COLUMN_USERNAME + " TEXT NOT NULL, " +
                UserContract.UserEntry.COLUMN_PASSWORD + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_USER_TABLE);

        final String SQL_CREATE_MEDICINE_TABLE = "CREATE TABLE " +
                MedicineContract.MedicineEntry.TABLE_NAME + " (" +
                MedicineContract.MedicineEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MedicineContract.MedicineEntry.COLUMN_MEDICINE_NAME + " TEXT NOT NULL, " +
                MedicineContract.MedicineEntry.COLUMN_REMINDER_TIME + " INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_MEDICINE_TABLE);

        // Add this inside your ReminderDbHelper class, inside the onCreate method
        final String SQL_CREATE_LABTEST_TABLE = "CREATE TABLE " +
                labTestContract.LabTestEntry.TABLE_NAME + " (" +
                labTestContract.LabTestEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                labTestContract.LabTestEntry.COLUMN_VENUE + " TEXT NOT NULL, " +
                labTestContract.LabTestEntry.COLUMN_LAB_TEST_NAME + " TEXT NOT NULL, " +
                labTestContract.LabTestEntry.COLUMN_REMINDER_TIME + " INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_LABTEST_TABLE);

        // Add this inside your ReminderDbHelper class, inside the onCreate method
        final String SQL_CREATE_HEALTHCHECK_TABLE = "CREATE TABLE " +
                HealthCheckContract.HealthCheckEntry.TABLE_NAME + " (" +
                HealthCheckContract.HealthCheckEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                HealthCheckContract.HealthCheckEntry.COLUMN_HEALTH_CHECK_NAME + " TEXT NOT NULL, " +
                HealthCheckContract.HealthCheckEntry.COLUMN_REMINDER_TIME + " INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_HEALTHCHECK_TABLE);



        // Create tables for other reminder types here, if needed
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades here, if needed
    }
}

