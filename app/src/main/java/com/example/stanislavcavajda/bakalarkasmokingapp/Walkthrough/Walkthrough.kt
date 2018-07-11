package com.example.stanislavcavajda.bakalarkasmokingapp.Walkthrough

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R

class Walkthrough : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.setTheme(this,Data.actualTheme)

        setContentView(R.layout.activity_walkthrough)

        var fm = supportFragmentManager
        fm.beginTransaction().setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left).add(R.id.container,WelcomeFragment()).commit()
    }
}
