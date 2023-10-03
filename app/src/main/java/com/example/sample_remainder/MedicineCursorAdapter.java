package com.example.sample_remainder;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.example.sample_remainder.data.MedicineContract;

public class MedicineCursorAdapter extends CursorAdapter {

    public MedicineCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.activity_medicine_list, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameTextView = view.findViewById(R.id.medicineNameEditText);
        TextView timeTextView = view.findViewById(R.id.medicineNameEditText);

        String medicineName = cursor.getString(cursor.getColumnIndexOrThrow(
                MedicineContract.MedicineEntry.COLUMN_MEDICINE_NAME));
        long reminderTime = cursor.getLong(cursor.getColumnIndexOrThrow(
                MedicineContract.MedicineEntry.COLUMN_REMINDER_TIME));

        nameTextView.setText(medicineName);
        timeTextView.setText("Time: " + formatTime(reminderTime));
    }

    private String formatTime(long timeInMillis) {
        // Implement the time formatting logic (e.g., converting milliseconds to a readable format)
        // You can use SimpleDateFormat or other methods here
        return "Formatted Time";
    }
}
