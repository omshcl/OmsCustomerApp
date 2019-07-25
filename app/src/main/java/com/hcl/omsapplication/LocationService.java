package com.hcl.omsapplication;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class LocationService extends Service {

    private static final int NOTIF_ID = 1;

    public LocationService() {}

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground();
        return super.onStartCommand(intent,flags,startId);
    }

    private void startForeground() {
        Toast.makeText(this,"service started",Toast.LENGTH_LONG).show();
        Intent notificationIntent = new Intent(this,CreateOrder.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);
        startForeground(1, new NotificationCompat.Builder(this,getString(R.string.channel_id))
                .setOngoing(true)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Service is running in the background")
                .setContentIntent(pendingIntent)
                .build());



    }
}
