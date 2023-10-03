package com.example.sample_remainder.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sample_remainder.labtest_remainder;

public class MedicineDbUtils {
    public static void insertMedicineReminder(Context context, String medicineName, long reminderTime) {
        ReminderDbHelper dbHelper = new ReminderDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MedicineContract.MedicineEntry.COLUMN_MEDICINE_NAME, medicineName);
        values.put(MedicineContract.MedicineEntry.COLUMN_REMINDER_TIME, reminderTime);

        db.insert(MedicineContract.MedicineEntry.TABLE_NAME, null, values);
        db.close();
    }
    public static void insertLabTestReminder(Context context, String labVenue, String labTestName, long reminderTime) {
        ReminderDbHelper dbHelper = new ReminderDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(labTestContract.LabTestEntry.COLUMN_VENUE, labVenue);
        values.put(labTestContract.LabTestEntry.COLUMN_LAB_TEST_NAME, labTestName);
        values.put(labTestContract.LabTestEntry.COLUMN_REMINDER_TIME, reminderTime);

        db.insert(labTestContract.LabTestEntry.TABLE_NAME, null, values);
        db.close();
    }

    public static void insertHealthCheckupReminder(Context context, String healthCheckName, long reminderTime) {
        ReminderDbHelper dbHelper = new ReminderDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HealthCheckContract.HealthCheckEntry.COLUMN_HEALTH_CHECK_NAME, healthCheckName);
        values.put(HealthCheckContract.HealthCheckEntry.COLUMN_REMINDER_TIME, reminderTime);

        db.insert(HealthCheckContract.HealthCheckEntry.TABLE_NAME, null, values);
        db.close();
    }


    // Define other database operations (query, update, delete) here

    public static Cursor queryMedicineReminders(Context context) {
        ReminderDbHelper dbHelper = new ReminderDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                MedicineContract.MedicineEntry.COLUMN_MEDICINE_NAME,
                MedicineContract.MedicineEntry.COLUMN_REMINDER_TIME
        };

        Cursor cursor = db.query(
                MedicineContract.MedicineEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        return cursor;
    }


    public static void updateMedicineReminder(Context context, long reminderId, long newReminderTime) {
        ReminderDbHelper dbHelper = new ReminderDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MedicineContract.MedicineEntry.COLUMN_REMINDER_TIME, newReminderTime);

        String whereClause = MedicineContract.MedicineEntry.COLUMN_ID + "=?";
        String[] whereArgs = { String.valueOf(reminderId) };

        db.update(
                MedicineContract.MedicineEntry.TABLE_NAME,
                values,
                whereClause,
                whereArgs
        );

        db.close();
    }



    public static void deleteMedicineReminder(Context context, long reminderId) {
        ReminderDbHelper dbHelper = new ReminderDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String whereClause = MedicineContract.MedicineEntry.COLUMN_ID + "=?";
        String[] whereArgs = { String.valueOf(reminderId) };

        db.delete(
                MedicineContract.MedicineEntry.TABLE_NAME,
                whereClause,
                whereArgs
        );
    }



}

