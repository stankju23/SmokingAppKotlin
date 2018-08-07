package com.example.stanislavcavajda.bakalarkasmokingapp.Main

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.Walkthrough.Walkthrough
import io.realm.Realm

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Realm.init(this)

        var preferences = getSharedPreferences("walkthrough", Context.MODE_PRIVATE)
        Data.firstTime = preferences.getBoolean(Constants.preferences.FIRST_TIME,false)

        if (!Data.firstTime) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                var walkthrough = Intent(this, Walkthrough::class.java)
                startActivity(walkthrough)
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),100)
                recreate()
            }


        } else {
            var preferences = getSharedPreferences("actualState",Context.MODE_PRIVATE)
            Data.MoneyDashboard.cigarretesPerDay = preferences.getInt(Constants.actualState.CIGARETTES_PER_DAY, 0)
            Data.MoneyDashboard.cigarretesInPackage = preferences.getInt(Constants.actualState.CIGARETTES_IN_PACKAGE, 0)
            Data.MoneyDashboard.packagePrice = preferences.getFloat(Constants.actualState.PACKAGE_PRICE,0f).toDouble()
            Data.MoneyDashboard.currency = preferences.getString(Constants.actualState.CURRENCY, "")
            Data.actualTheme = preferences.getInt(Constants.actualState.THEME,0)
            Data.date = preferences.getString(Constants.actualState.ACTUAL_DATE,"")

            var mainActivity = Intent(this,MainActivity::class.java)
            startActivity(mainActivity)
        }
    }
}
