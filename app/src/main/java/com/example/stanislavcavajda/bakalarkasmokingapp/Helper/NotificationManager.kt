package com.example.stanislavcavajda.bakalarkasmokingapp.Helper

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import com.example.stanislavcavajda.bakalarkasmokingapp.Main.MainActivity

class NotificationManager: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && intent != null) {

            var title = intent.getStringExtra("notificationTitle")
            var desc = intent.getStringExtra("notificationDesc")
            var image = intent.getIntExtra("notificationImage",0)

            var mainActivity = Intent(context,MainActivity::class.java)
            mainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            var pendingIntent = PendingIntent.getActivity(context,0,mainActivity,PendingIntent.FLAG_UPDATE_CURRENT)


            var builder = NotificationCompat.Builder(context)
            builder.setSmallIcon(image)
            builder.setContentTitle(title)
            builder.setContentText(desc)
            builder.setContentIntent(pendingIntent)
            builder.setAutoCancel(true)
            builder.setPriority(NotificationCompat.PRIORITY_DEFAULT)

            var notificationManager = NotificationManagerCompat.from(context)
            notificationManager.notify(0,builder.build())
        }
    }

}