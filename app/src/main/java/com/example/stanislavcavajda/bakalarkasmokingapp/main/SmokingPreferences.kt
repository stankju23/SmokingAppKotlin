package com.example.stanislavcavajda.bakalarkasmokingapp.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivitySmokingPreferencesBinding

class SmokingPreferences : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.setTheme(this,Data.actualTheme)
        var binding:ActivitySmokingPreferencesBinding = ActivitySmokingPreferencesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var prefViewModel = PreferencesViewModel(Data.MoneyDashboard.cigarretesPerDay.toString()
            , Data.MoneyDashboard.cigarretesInPackage.toString()
            , Data.MoneyDashboard.packagePrice.toString(),this,Data.MoneyDashboard.currency)

        binding.viewModel = prefViewModel

        setSupportActionBar(binding.smokingPreferencesToolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)
        supportActionBar?.title = getString(R.string.smoking_preferences)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
