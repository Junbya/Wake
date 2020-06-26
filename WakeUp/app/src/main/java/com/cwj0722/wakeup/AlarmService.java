package com.cwj0722.wakeup;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class AlarmService extends Service {
    public IBinder onBind(Intent intent){
        return null;
    }
    public int onStartCommand(Intent intent, int flags, int startld) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelld = createNotificationChannel();

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelld);
            Notification notification =builder.setOngoing(true).setSmallIcon(R.mipmap.ic_launcher).build();

            startForeground(1,notification);
        }

        Intent intent1 = new Intent(this, AlarmActivity.class);

        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent1);

        Log.d("AlarmService", "Alarm");

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            stopForeground(true);
        }

        return START_NOT_STICKY;
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private String createNotificationChannel() {
        String channelld = "Alarm";
        String channelName = getString(R.string.app_name);
        NotificationChannel channel = new NotificationChannel(channelld, channelName, NotificationManager.IMPORTANCE_NONE);

        channel.setSound(null,null);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);

        return channelld;
    }
}
