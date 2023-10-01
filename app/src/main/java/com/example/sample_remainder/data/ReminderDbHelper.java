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
        final String SQL_CREATE_MEDICINE_TABLE = "CREATE TABLE " +
                MedicineContract.MedicineEntry.TABLE_NAME + " (" +
                MedicineContract.MedicineEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MedicineContract.MedicineEntry.COLUMN_MEDICINE_NAME + " TEXT NOT NULL, " +
                MedicineContract.MedicineEntry.COLUMN_REMINDER_TIME + " INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_MEDICINE_TABLE);

        // Create tables for other reminder types here, if needed
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades here, if needed
    }
}

