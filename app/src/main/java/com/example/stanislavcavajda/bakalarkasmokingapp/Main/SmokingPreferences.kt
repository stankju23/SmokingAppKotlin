package com.example.stanislavcavajda.bakalarkasmokingapp.Main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivitySmokingPreferencesBinding
import kotlinx.android.synthetic.main.activity_smoking_preferences.*

class SmokingPreferences : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.setTheme(this,Data.actualTheme)
        var binding:ActivitySmokingPreferencesBinding = DataBindingUtil.setContentView(this, R.layout.activity_smoking_preferences)

        var prefViewModel = PreferencesViewModel(Data.MoneyDashboard.cigarretesPerDay.toString()
            , Data.MoneyDashboard.cigarretesInPackage.toString()
            , Data.MoneyDashboard.packagePrice.toString(),this,Data.MoneyDashboard.currency)

        binding.viewModel = prefViewModel

        setSupportActionBar(smoking_preferences_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)
        supportActionBar?.title = getString(R.string.smoking_preferences)

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
