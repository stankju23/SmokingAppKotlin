package com.example.stanislavcavajda.bakalarkasmokingapp.Main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.Walkthrough.Walkthrough

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var preferences = getSharedPreferences("walkthrough", Context.MODE_PRIVATE)
        var firstTime = preferences.getBoolean(Constants.preferences.FIRST_TIME,false)

        var walkthrough = Intent(this,Walkthrough::class.java)
        startActivity(walkthrough)

//        if (!firstTime) {
////            var walkthrough = Intent(this, Walkthrough::class.java)
////            startActivity(walkthrough)
//            var mainActivity = Intent(this,MainActivity::class.java)
//            startActivity(mainActivity)
//        } else {
//            var mainActivity = Intent(this,MainActivity::class.java)
//            startActivity(mainActivity)
//        }
    }
}
