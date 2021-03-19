 package com.example.s3.extra;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.s3.Attendancee.Attendance;
import com.example.s3.R;

 public class ReminderBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


     //   Intent notificationIntent2=new Intent(context,ServiceReminder.class);
      //context.startService(notificationIntent2);
       // Log.d("ttt","hey2");


//if(intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){

       // long when =System.currentTimeMillis();
    //    NotificationManager notificationManager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent=new Intent(context, Attendance.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
       // PendingIntent pendingIntent=PendingIntent.getActivity(context,100,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
PendingIntent pendingIntent=PendingIntent.getActivity(context,0,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        Log.d("ttt","hey2");

        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,"notify")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.tree)
                .setContentTitle("Time to fill Attendance")
                .setContentText("ffggjh")
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("cfffffffffffff"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Log.d("ttt","hey3");

        NotificationManagerCompat notificationManager=NotificationManagerCompat.from(context);
       // if(notificationIntent.getAction().equals("MY_NOTIFICATION_MESSAGE")) {
            notificationManager.notify(200, builder.build());
            Log.d("ttt","hey");
        //h}
    }
}
