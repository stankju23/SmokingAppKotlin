package com.example.stanislavcavajda.bakalarkasmokingapp.walkthrough

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R

class Walkthrough : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.setTheme(this,Data.actualTheme)

        setContentView(R.layout.activity_walkthrough)

        var fm = supportFragmentManager
        fm.beginTransaction().setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left).add(R.id.container,WelcomeFragment()).commit()
    }

    override fun onBackPressed() {

    }
}
