package com.example.stanislavcavajda.bakalarkasmokingapp.missions

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityObjectivesBinding

class ObjectivesActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this,Data.actualTheme)

        var position = Data.actualClickedMission

        Log.i("Objective position", "${Data.actualClickedActivity}")

        var binding:ActivityObjectivesBinding = ActivityObjectivesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewModel = ActivityListViewModel(Data.missionList[position].activities,this)

        viewPager = findViewById<ViewPager>(R.id.activity_view_pager)
        viewPager.offscreenPageLimit = 5
        viewPager.post(object : Runnable {
            override fun run() {
                viewPager.setCurrentItem(Data.actualClickedActivity,false)
            }
        })


        binding.dots.setupWithViewPager(viewPager)

        setSupportActionBar(binding.objectivesToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)
        supportActionBar?.title = Data.missionList[position].name
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                Data.actualClickedActivity = 0
                onBackPressed()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        Data.actualClickedActivity = 0
        super.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
    }
}
