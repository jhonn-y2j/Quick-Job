package com.developers.quickjob.quick_job.service;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.developers.quickjob.quick_job.LoginActivity;
import com.developers.quickjob.quick_job.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by jhonn_aj on 30/11/2016.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);

        Intent i = new Intent(this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder= new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.publicar_oferta);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.imagen_logo));
        builder.setAutoCancel(true);
        builder.setVibrate(new long[] {100, 250, 100, 500});
        if (remoteMessage.getNotification()==null){
            builder.setContentText(remoteMessage.getData().get("message"));
            builder.setContentTitle(remoteMessage.getData().get("title"));
        }else {
            builder.setContentTitle(remoteMessage.getNotification().getTitle());
            builder.setContentText(remoteMessage.getNotification().getBody());
        }
        builder.setSound(sonido);
        builder.setContentIntent(pendingIntent);
        //builder.setSubText(remoteMessage.getFrom());

        NotificationManager mNotifyMgr =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotifyMgr.notify(0,builder.build());

        //Log.e(MyFirebaseMessagingService.class.getName(), remoteMessage.getNotification().getBody());

    }
}
