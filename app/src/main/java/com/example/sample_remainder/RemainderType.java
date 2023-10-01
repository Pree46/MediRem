package com.example.sample_remainder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class RemainderType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remainder_type);

        Button medicineButton = findViewById(R.id.medicineButton);
        Button labTestButton = findViewById(R.id.labTestButton);
        Button healthCheckButton = findViewById(R.id.healthCheckButton);

        medicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startReminderActivity(medicine_remainder.class);
            }
        });

        labTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startReminderActivity(labtest_remainder.class);
            }
        });

        healthCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startReminderActivity(healthcheck_remainder.class);
            }
        });
    }

    private void startReminderActivity(Class<?> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }
}
