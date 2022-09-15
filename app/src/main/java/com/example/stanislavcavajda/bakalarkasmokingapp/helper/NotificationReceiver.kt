package com.example.stanislavcavajda.bakalarkasmokingapp.helper

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.stanislavcavajda.bakalarkasmokingapp.main.MainActivity

class NotificationReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && intent != null) {

            var notificationManager: NotificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            var title = intent.getStringExtra(Constants.Notification.title)
            var desc = intent.getStringExtra(Constants.Notification.desc)
            var image = intent.getIntExtra(Constants.Notification.image,0)
            var id = intent.getIntExtra(Constants.Notification.id,0)
            var wish = intent.getBooleanExtra("wish",false)


            var mainActivity = Intent(context,MainActivity::class.java)
            mainActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            if (wish) {
                mainActivity.putExtra("wishes", true)
            }
            var pendingIntent = PendingIntent.getActivity(context,id,mainActivity,PendingIntent.FLAG_UPDATE_CURRENT)

            var builder = NotificationCompat.Builder(context)
            builder.setSmallIcon(image)
            builder.setContentTitle(title)
            builder.setContentText(desc)
            builder.setContentIntent(pendingIntent)
            builder.setAutoCancel(true)
            builder.setChannelId(Data.notificationChannel)
            notificationManager.notify(id,builder.build())
        }
    }


}