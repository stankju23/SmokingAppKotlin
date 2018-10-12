package com.example.stanislavcavajda.bakalarkasmokingapp.Main

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.DataManager
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.Walkthrough.Walkthrough
import io.realm.Realm

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Realm.init(this)

        ThemeManager.setTheme(this,Data.actualTheme)



        var preferences = getSharedPreferences("walkthrough", Context.MODE_PRIVATE)
        Data.firstTime = preferences.getBoolean(Constants.preferences.FIRST_TIME,false)

        if (!Data.firstTime) {

            var mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            var name = "Notification Channel"
            var desc = "Toto je novy notifikacny channel"

            var importance = NotificationManager.IMPORTANCE_DEFAULT
            if (android.os.Build.VERSION.SDK_INT >=  android.os.Build.VERSION_CODES.O) {
                var mChannel = NotificationChannel(Data.notificationChannel,name,importance)
                mChannel.description = desc
                mChannel.enableLights(true)
                mChannel.lightColor = Color.RED
                mChannel.enableVibration(true)
                mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
                mNotificationManager.createNotificationChannel(mChannel)
            }

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                var walkthrough = Intent(this, Walkthrough::class.java)
                startActivity(walkthrough)
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),100)
                recreate()
            }


        } else {
            DataManager.loadMainData(this)

            var mainActivity = Intent(this,MainActivity::class.java)
            startActivity(mainActivity)
        }
    }
}
