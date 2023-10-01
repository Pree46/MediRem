package com.example.sample_remainder;

import android.app.Application;
import com.example.sample_remainder.data.ReminderDbHelper;

import com.facebook.stetho.Stetho;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        ReminderDbHelper dbHelper = new ReminderDbHelper(this);
    }
}


