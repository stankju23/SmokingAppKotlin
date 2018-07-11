package com.example.stanislavcavajda.bakalarkasmokingapp.Main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.Walkthrough.Walkthrough

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var preferences = getSharedPreferences("walkthrough", Context.MODE_PRIVATE)
        var firstTime = preferences.getBoolean(Constants.preferences.FIRST_TIME,false)


        if (!firstTime) {

            var walkthrough = Intent(this, Walkthrough::class.java)
            startActivity(walkthrough)

        } else {

            var preferences = getSharedPreferences("actualState",Context.MODE_PRIVATE)
            Data.MoneyDashboard.cigarretesPerDay = preferences.getInt(Constants.actualState.CIGARETTES_PER_DAY, 0)
            Data.MoneyDashboard.cigarretesInPackage = preferences.getInt(Constants.actualState.CIGARETTES_IN_PACKAGE, 0)
            Data.MoneyDashboard.packagePrice = preferences.getFloat(Constants.actualState.PACKAGE_PRICE,0f).toDouble()
            Data.MoneyDashboard.currency = preferences.getString(Constants.actualState.CURRENCY, "")
            Data.date = preferences.getString(Constants.actualState.ACTUAL_DATE,"")

            var mainActivity = Intent(this,MainActivity::class.java)
            startActivity(mainActivity)
        }
    }
}
