package com.example.stanislavcavajda.bakalarkasmokingapp.Missions

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityObjectivesBinding
import kotlinx.android.synthetic.main.activity_objectives.*
import me.relex.circleindicator.CircleIndicator

class ObjectivesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this,Data.actualTheme)

        var position = intent.getIntExtra(Constants.extras.EXTRA_ITEM_ID,0)

        var binding:ActivityObjectivesBinding = DataBindingUtil.setContentView(this,R.layout.activity_objectives)
        binding.viewModel = ActivityListViewModel(Data.missionList[position].activities!!)

        var indicator = findViewById<CircleIndicator>(R.id.indicator)
        var viewPager = findViewById<ViewPager>(R.id.activity_view_pager)
        viewPager.offscreenPageLimit = 5
        indicator.setViewPager(viewPager)

        setSupportActionBar(objectives_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)
        supportActionBar?.title = Data.missionList[position].name
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
