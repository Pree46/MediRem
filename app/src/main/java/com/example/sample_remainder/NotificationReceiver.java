package com.example.sample_remainder;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID_MEDICINE = "medicine_notification_channel";
    private static final String CHANNEL_ID_LABTEST = "labtest_notification_channel";
    private static final String CHANNEL_ID_HEALTHCHECK = "healthcheck_notification_channel";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null) {
            switch (action) {
                case "MEDICINE_REMINDER_ACTION":
                    handleReminder(context, intent, CHANNEL_ID_MEDICINE);
                    break;
                case "HEALTH_CHECKUP_REMINDER_ACTION":
                    handleReminder(context, intent, CHANNEL_ID_HEALTHCHECK);
                    break;
                case "LAB_TEST_REMINDER_ACTION":
                    handleReminder(context, intent, CHANNEL_ID_LABTEST);
                    break;
                default:
                    // Handle other cases or do nothing
            }
        }
    }

    private void handleReminder(Context context, Intent intent, String channelId) {
        int notificationId = intent.getIntExtra("notificationId", 0);
        String reminderMessage = intent.getStringExtra("reminderMessage");

        createNotification(context, channelId, notificationId, "Reminder", reminderMessage);
    }

    private void createNotification(Context context, String channelId, int notificationId, String title, String message) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager, channelId);
        }

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.baseline_notifications_active_24)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        notificationManager.notify(notificationId, builder.build());
    }

    private void createNotificationChannel(NotificationManager notificationManager, String channelId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence channelName = "Notification Channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
