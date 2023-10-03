package com.example.sample_remainder.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDbUtils {
    public static boolean isValidUser(Context context, String username, String password) {
        ReminderDbHelper dbHelper = new ReminderDbHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                UserContract.UserEntry.COLUMN_USERNAME,
                UserContract.UserEntry.COLUMN_PASSWORD
        };

        String selection = UserContract.UserEntry.COLUMN_USERNAME + " = ? AND " +
                UserContract.UserEntry.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(
                UserContract.UserEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        boolean isValid = cursor.moveToFirst();
        cursor.close();
        db.close();

        return isValid;
    }
}

