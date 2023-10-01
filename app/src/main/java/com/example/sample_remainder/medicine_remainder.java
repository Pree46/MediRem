package com.example.sample_remainder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sample_remainder.data.MedicineDbUtils;

import java.util.Calendar;

public class medicine_remainder extends AppCompatActivity {

    private DatePicker datePicker;
    private TimePicker timePicker;
    private RadioGroup medicineTypeRadioGroup;
    private EditText medicineName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_remainder);

        medicineName = findViewById(R.id.medicineNameEditText);
        datePicker = findViewById(R.id.medicineDatePicker);
        timePicker = findViewById(R.id.medicineTimePicker);
        medicineTypeRadioGroup = findViewById(R.id.medicineTypeRadioGroup);

        Button confirmButton = findViewById(R.id.confirmMedicineReminderButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                scheduleNotification();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void scheduleNotification() {
        int year = datePicker.getYear();
        int month = datePicker.getMonth();
        int day = datePicker.getDayOfMonth();
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute, 0);

        int selectedMedicineType = medicineTypeRadioGroup.getCheckedRadioButtonId();
        String medicineType = "";

        switch (selectedMedicineType) {
            case R.id.syringeRadioButton:
                medicineType = "Syringe";
                break;
            case R.id.syrupRadioButton:
                medicineType = "Syrup";
                break;
            case R.id.pillsRadioButton:
                medicineType = "Pills";
                break;
            case R.id.tabletRadioButton:
                medicineType = "Tablet";
                break;
        }

        String medName = medicineName.getText().toString();

        String notificationMessage = "Time to take your " + medicineType;

        MedicineDbUtils.insertMedicineReminder(this, medName, calendar.getTimeInMillis());

        // Set up the notification
        setNotificationAlarm(this, notificationMessage, calendar.getTimeInMillis());
        Intent intent = new Intent(this, MedicineListActivity.class);
        startActivity(intent);
    }

    // Method to set up the notification alarm
    private void setNotificationAlarm(Context context, String message, long timeInMillis) {
        int notificationId = (int) System.currentTimeMillis();

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent notificationIntent = new Intent(context, NotificationReceiver.class);
        notificationIntent.setAction("MEDICINE_REMINDER_ACTION");
        notificationIntent.putExtra("notificationId", notificationId);
        notificationIntent.putExtra("reminderMessage", message);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                notificationId,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        // Set the alarm
        alarmManager.set(AlarmManager.RTC_WAKEUP, timeInMillis, pendingIntent);

        Toast.makeText(this, "Medicine reminder set!", Toast.LENGTH_SHORT).show();
    }
}
