package com.example.root.farmerapp2;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by root on 17/05/16.
 */
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.gcm.GcmListenerService;

import models.Notification;
import models.User;

public class MyGcmListenerService extends GcmListenerService {

    private static final String TAG = "MyGcmListenerService";
    ListView notList; //Initializing notification list


    /**
     * Called when message is received.
     *
     * @param from SenderID of the sender.
     * @param data Data bundle containing message data as key/value pairs.
     *             For Set of keys use data.keySet().
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");
        Log.d(TAG, "From: " + from);
        Log.d(TAG, " " + message);

//        Notification notification_item = new Notification();
//        Notification notification_item_eng = new Notification();
//        if(message.equals("لديك نصيحة جديدة")) {
//            notification_item.setMessage(message);
//            notification_item.setNotifyIcon(R.drawable.ic_bell_black);
//            ThreeFragment.notify.add(notification_item);
//        }
//        else {
//            if (message.equals("لقد جاوب المهندس على سؤالك")) {
//                notification_item.setMessage(message);
//                notification_item.setNotifyIcon(R.drawable.ic_bell);
//                ThreeFragment.notify.add(notification_item);
//            } else {
//                notification_item.setMessage("ليس لديك اى نصائح او اسئلة");
//                notification_item.setNotifyIcon(R.drawable.ic_bell_white);
//            }
//        }
//        ThreeFragment.notify.clear();
//        ThreeFragment.notify.add(notification_item);
//        ThreeFragment.adapter.notifyDataSetChanged();
//


        // [START_EXCLUDE]
        /**
         * Production applications would usually process the message here.
         * Eg: - Syncing with server.
         *     - Store message in local database.
         *     - Update UI.
         */

        /**
         * In some cases it may be useful to show a notification indicating to the user
         * that a message was received.
         */
        sendNotification(message);
        // [END_EXCLUDE]
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received GCM message.
     *
     * @param message GCM message received.
     */
    private void sendNotification(String message) {
        SharedPreferences sp = getSharedPreferences("sharedPreferences", Activity.MODE_PRIVATE);
        String userStatus = sp.getString("status", "");



            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_plant)
                    .setContentTitle("Egypt Agri App")
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
        }
    }
