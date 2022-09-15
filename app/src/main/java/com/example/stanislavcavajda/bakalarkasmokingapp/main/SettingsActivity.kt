package com.example.stanislavcavajda.bakalarkasmokingapp.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.feedback.FeedbackActivity
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this, Data.actualTheme)

        val binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.settingsToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)
        supportActionBar?.title = getString(R.string.settings_title)
        binding.changeColorButton.setOnClickListener {
            var changeColorActivity = Intent(this,ChangeColor::class.java)
            startActivity(changeColorActivity)
        }
        binding.smokingPreferencesBtn.setOnClickListener {
            var smokingPreferences = Intent(this,SmokingPreferences::class.java)
            startActivity(smokingPreferences)
        }

        binding.feedbackButton.setOnClickListener {
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
