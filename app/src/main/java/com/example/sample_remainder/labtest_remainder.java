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
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sample_remainder.data.MedicineDbUtils;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class labtest_remainder extends AppCompatActivity {

    private DatePicker datePicker;
    private TimePicker timePicker;
    public TextInputEditText labtesttype;
    public TextInputEditText labtestVenue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labtest_remainder);

        datePicker = findViewById(R.id.labTestDatePicker);
        timePicker = findViewById(R.id.labTestTimePicker);
        labtesttype = findViewById(R.id.labTestTypeEditText);
        labtestVenue = findViewById(R.id.labVenueEditText);



        Button confirmButton = findViewById(R.id.confirmLabTestButton);
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

        String labtest = labtesttype.getText().toString();
        String labVenue = labtestVenue.getText().toString();

        String notificationMessage = "Time for your lab test";

        Context context = getApplicationContext(); // Replace this with the appropriate context
        MedicineDbUtils.insertLabTestReminder(context, labVenue, labtest, calendar.getTimeInMillis());

        // Set up the notification
        setNotificationAlarm(this, notificationMessage, calendar.getTimeInMillis());
    }

    // Method to set up the notification alarm
    private void setNotificationAlarm(Context context, String message, long timeInMillis) {
        int notificationId = (int) System.currentTimeMillis();

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent notificationIntent = new Intent(context, NotificationReceiver.class);
        notificationIntent.setAction("LAB_TEST_REMINDER_ACTION");
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

        Toast.makeText(this, "Lab test reminder set!", Toast.LENGTH_SHORT).show();
    }
}
