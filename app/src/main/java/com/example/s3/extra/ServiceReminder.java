package com.example.s3.extra;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.s3.Attendancee.Attendance;
import com.example.s3.R;

public class ServiceReminder extends IntentService {


    public ServiceReminder(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("ttt","hey3");

        NotificationManager notificationManager= (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent=new Intent(this, Attendance.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext(),"notify")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.tree)
                .setContentTitle("Time to fill Attendance")
                .setContentText("ffggjh")
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("cfffffffffffff"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

      //  NotificationManagerCompat notificationManager=NotificationManagerCompat.from(this);
        Log.d("ttt","hey4");

        notificationManager.notify(200, builder.build());


    }
}
