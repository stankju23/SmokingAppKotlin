package com.example.stanislavcavajda.bakalarkasmokingapp.Missions

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityMissionInfoBinding
import kotlinx.android.synthetic.main.activity_mission_info.*
import java.util.Timer

class MissionInfoActivity : AppCompatActivity() {

    var missionPosition = 0

    var timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this,Data.actualTheme)

        var binding:ActivityMissionInfoBinding = DataBindingUtil.setContentView(this,R.layout.activity_mission_info)

        this.missionPosition = intent.getIntExtra(Constants.extras.EXTRA_ITEM_ID,0)

        var viewModel = ActivityListViewModel(Data.missionList[missionPosition].activities)


        binding.viewModel = viewModel
        binding.missionInfo = Data.missionList[missionPosition]


        button5.setOnClickListener {
            var intent = Intent(this,ObjectivesActivity::class.java)
            intent.putExtra(Constants.extras.EXTRA_ITEM_ID,this.missionPosition)
            startActivity(intent)
        }



        setSupportActionBar(mission_info_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)
        supportActionBar?.title = Data.missionList[missionPosition].name
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

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()
    }
}
