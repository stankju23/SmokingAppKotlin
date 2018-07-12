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


        var theme1 = findViewById<ConstraintLayout>(R.id.theme1)
        theme1.setOnClickListener {
            Data.actualTheme = Constants.Themes.theme1
            actualStateEditor?.putInt(Constants.actualState.THEME, Data.actualTheme)
            actualStateEditor?.commit()
            Data.themeChanged = true
            ThemeManager.setTheme(this,Data.actualTheme)
            recreate()
        }

        var theme2 = findViewById<ConstraintLayout>(R.id.theme2)
        theme2.setOnClickListener {
            Data.actualTheme = Constants.Themes.theme2
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
