package com.example.stanislavcavajda.bakalarkasmokingapp.Missions

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityObjectivesBinding
import kotlinx.android.synthetic.main.activity_objectives.*

class ObjectivesActivity : AppCompatActivity() {

    lateinit var viewPager:ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this,Data.actualTheme)

        var position = Data.actualClickedMission

        Log.i("Objective position", "${Data.actualClickedActivity}")

        var binding:ActivityObjectivesBinding = DataBindingUtil.setContentView(this,R.layout.activity_objectives)
        binding.viewModel = ActivityListViewModel(Data.missionList[position].activities!!,this)

        viewPager = findViewById<ViewPager>(R.id.activity_view_pager)
        viewPager.offscreenPageLimit = 5
        viewPager.post(object : Runnable {
            override fun run() {
                viewPager.setCurrentItem(Data.actualClickedActivity,false)
            }
        })


        dots.setupWithViewPager(viewPager)

        setSupportActionBar(objectives_toolbar)
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
