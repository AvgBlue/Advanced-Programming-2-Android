package com.example.advanced_programming_2_android;

import android.app.NotificationManager;
        import android.app.PendingIntent;
        import android.content.Context;
        import android.content.Intent;

import android.util.Log;



import androidx.annotation.NonNull;
        import androidx.core.app.NotificationCompat;

        import com.google.firebase.messaging.FirebaseMessagingService;
        import com.google.firebase.messaging.RemoteMessage;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class firebaseService extends FirebaseMessagingService {
    private static final int NOTIFICATION_ID = 123;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        // Retrieve the message data
        String title = remoteMessage.getNotification().getTitle();
        String body = remoteMessage.getNotification().getBody();


        // Create an intent for the notification's click action
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        // Build the notification
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, "channel_id")
                        .setSmallIcon(R.drawable.chit_chat_logo)
                        .setContentTitle(title)
                        .setContentText(body)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);

        // Show the notification
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
    }
}