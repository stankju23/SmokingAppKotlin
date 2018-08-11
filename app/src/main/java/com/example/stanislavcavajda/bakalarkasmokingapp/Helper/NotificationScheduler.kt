package com.example.stanislavcavajda.bakalarkasmokingapp.Helper

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock

class NotificationScheduler() {

    fun scheduleNotification(title:String, desc:String,image:Int,context: Context,id:Int,time:Long) {

        var notificationIntent = Intent(context, NotificationReceiver::class.java)
        notificationIntent.putExtra(Constants.Notification.title,title)
        notificationIntent.putExtra(Constants.Notification.desc,desc)
        notificationIntent.putExtra(Constants.Notification.image,image)
        notificationIntent.setAction(Intent.ACTION_SEND)
        var pendingIntent = PendingIntent.getBroadcast(context,id,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT)

        var alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + (time * 1000),pendingIntent)
    }
}