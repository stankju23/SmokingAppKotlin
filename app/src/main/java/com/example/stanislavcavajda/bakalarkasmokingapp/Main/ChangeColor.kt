package com.example.stanislavcavajda.bakalarkasmokingapp.Main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import kotlinx.android.synthetic.main.activity_change_color.*

class ChangeColor : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this,Data.actualTheme)

        setContentView(R.layout.activity_change_color)


        theme1.setOnClickListener {
            Data.actualTheme = Constants.Themes.theme1
            Data.themeChanged = true
            this.setTheme(R.style.AppTheme)
            recreate()
        }

        theme2.setOnClickListener {
            Data.actualTheme = Constants.Themes.theme2
            Data.themeChanged = true
            this.setTheme(R.style.Theme1)
            recreate()
        }
    }
}
