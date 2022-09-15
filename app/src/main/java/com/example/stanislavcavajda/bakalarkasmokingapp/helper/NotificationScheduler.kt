package com.example.stanislavcavajda.bakalarkasmokingapp.helper

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock

class NotificationScheduler() {

    fun scheduleNotification(title:String, desc:String,image:Int,context: Context,id:Int,time:Long,wish:Boolean,delete:Boolean) {

        var notificationIntent = Intent(context, NotificationReceiver::class.java)
        notificationIntent.putExtra(Constants.Notification.title,title)
        notificationIntent.putExtra(Constants.Notification.desc,desc)
        notificationIntent.putExtra(Constants.Notification.image,image)
        notificationIntent.putExtra(Constants.Notification.id,id)
        notificationIntent.putExtra("wish",wish)
        notificationIntent.setAction(Intent.ACTION_SEND)
        var pendingIntent = PendingIntent.getBroadcast(context,id,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT)
        var alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + (time * 1000),pendingIntent)
    }


    fun cancelNotification(id:Int,context: Context){
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val myIntent = Intent(context, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context, id, myIntent,
            PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.cancel(pendingIntent)
    }
}