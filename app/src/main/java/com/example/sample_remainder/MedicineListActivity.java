package com.example.sample_remainder;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sample_remainder.data.MedicineContract;
import com.example.sample_remainder.data.MedicineDbUtils;

public class MedicineListActivity extends AppCompatActivity {

    private ListView listView;
    private MedicineCursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_list);

        listView = findViewById(R.id.medicineListView);
        cursorAdapter = new MedicineCursorAdapter(this, null);
        listView.setAdapter(cursorAdapter);

        // Load medicine reminders from the database
        loadMedicineReminders();
    }

    private void loadMedicineReminders() {

        // Query the database for medicine reminders
        Cursor cursor = MedicineDbUtils.queryMedicineReminders(this);

        // Update the cursor in the adapter to refresh the list
        cursorAdapter.swapCursor(cursor);
    }

}
