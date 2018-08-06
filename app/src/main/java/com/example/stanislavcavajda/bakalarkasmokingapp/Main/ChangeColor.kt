package com.example.stanislavcavajda.bakalarkasmokingapp.Main

import android.content.Context
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
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

        setSupportActionBar(change_color_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)
        supportActionBar?.title = getString(R.string.change_color_title)
        var actualStatePreferences = getSharedPreferences("actualState", Context.MODE_PRIVATE)
        var actualStateEditor = actualStatePreferences?.edit()


        var appTheme = findViewById<ConstraintLayout>(R.id.app_theme)
        appTheme.setOnClickListener {
            Data.actualTheme = Constants.Themes.pastel
            actualStateEditor?.putInt(Constants.actualState.THEME, Data.actualTheme)
            actualStateEditor?.commit()
            Data.themeChanged = true
            ThemeManager.setTheme(this,Data.actualTheme)
            recreate()
        }

        var blueOcean = findViewById<ConstraintLayout>(R.id.blue_ocean)
        blueOcean.setOnClickListener {
            Data.actualTheme = Constants.Themes.blueOcean
            actualStateEditor?.putInt(Constants.actualState.THEME, Data.actualTheme)
            actualStateEditor?.commit()
            Data.themeChanged = true
            ThemeManager.setTheme(this,Data.actualTheme)
            recreate()
        }

        var wine = findViewById<ConstraintLayout>(R.id.wine)
        wine.setOnClickListener {
            Data.actualTheme = Constants.Themes.wine
            actualStateEditor?.putInt(Constants.actualState.THEME, Data.actualTheme)
            actualStateEditor?.commit()
            Data.themeChanged = true
            ThemeManager.setTheme(this,Data.actualTheme)
            recreate()
        }

        var banana = findViewById<ConstraintLayout>(R.id.banana)
        banana.setOnClickListener {
            Data.actualTheme = Constants.Themes.banana
            actualStateEditor?.putInt(Constants.actualState.THEME, Data.actualTheme)
            actualStateEditor?.commit()
            Data.themeChanged = true
            ThemeManager.setTheme(this,Data.actualTheme)
            recreate()
        }



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
