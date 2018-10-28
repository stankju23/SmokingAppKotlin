package com.example.stanislavcavajda.bakalarkasmokingapp.Main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.Feedback.FeedbackActivity
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this, Data.actualTheme)

        setContentView(R.layout.activity_settings)

        setSupportActionBar(settings_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)
        supportActionBar?.title = getString(R.string.settings_title)
        change_color_button.setOnClickListener {
            var changeColorActivity = Intent(this,ChangeColor::class.java)
            startActivity(changeColorActivity)
        }
        smoking_preferences_btn.setOnClickListener {
            var smokingPreferences = Intent(this,SmokingPreferences::class.java)
            startActivity(smokingPreferences)
        }

        feedback_button.setOnClickListener {
            var feedbackActivity = Intent(this,FeedbackActivity::class.java)
            startActivity(feedbackActivity)
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

    override fun onRestart() {
        if(Data.themeChanged) {
            ThemeManager.setTheme(this,Data.actualTheme)
            this.recreate()
        }
        super.onRestart()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
