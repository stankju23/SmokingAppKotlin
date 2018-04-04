package com.example.stanislavcavajda.bakalarkasmokingapp.Missions

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityMissionInfoBinding
import kotlinx.android.synthetic.main.activity_mission_info.*

class MissionInfoActivity : AppCompatActivity() {

    var missionPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding:ActivityMissionInfoBinding = DataBindingUtil.setContentView(this,R.layout.activity_mission_info)

        this.missionPosition = intent.getIntExtra(Constants.extras.EXTRA_ITEM_ID,0)

        binding.viewModel = ActivityListViewModel(Data.missionList[missionPosition].activities!!)

        button5.setOnClickListener {
            var intent = Intent(this,ObjectivesActivity::class.java)
            intent.putExtra(Constants.extras.EXTRA_ITEM_ID,this.missionPosition)
            startActivity(intent)
        }


        setSupportActionBar(mission_info_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_material)
        supportActionBar?.title = Data.missionList[missionPosition].name
    }
}
