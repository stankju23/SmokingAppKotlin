package com.example.stanislavcavajda.bakalarkasmokingapp.Missions

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
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

        this.missionPosition = Data.actualClickedMission

        var viewModel = ActivityListViewModel(Data.missionList[missionPosition].activities,this)


        binding.viewModel = viewModel
        binding.missionInfo = Data.missionList[missionPosition]


        button5.setOnClickListener {
            var intent = Intent(this,ObjectivesActivity::class.java)
            intent.putExtra(Constants.extras.EXTRA_ITEM_ID,this.missionPosition)
            startActivity(intent)
        }

        Data.missionList[missionPosition].getDone()
        number_of_done.setText("${resources.getString(R.string.objectives)} ${Data.missionList[missionPosition].done}/5")

        if (Data.missionList[missionPosition].done == 5) {
            setLayoutBackground(mission_info_layout,this,true)
        } else {
            setLayoutBackground(mission_info_layout,this,false)
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

    override fun onResume() {
        Data.missionList[missionPosition].getDone()
        number_of_done.setText("${resources.getString(R.string.objectives)} ${Data.missionList[missionPosition].done}/5")

        if (Data.missionList[missionPosition].done == 5) {
            setLayoutBackground(mission_info_layout,this,true)
        } else {
            setLayoutBackground(mission_info_layout,this,false)
        }

        super.onResume()
    }

    fun setLayoutBackground(layout:ConstraintLayout, context: Context,isDone:Boolean) {
        if (isDone){
            val value = TypedValue()
            layout.context.getTheme().resolveAttribute(R.attr.colorPrimary, value, true)
            layout.setBackgroundColor(value.data)
        } else {
            val value = TypedValue()
            layout.context.getTheme().resolveAttribute(R.attr.colorLight, value, true)
            layout.setBackgroundColor(value.data)
        }
    }
}
