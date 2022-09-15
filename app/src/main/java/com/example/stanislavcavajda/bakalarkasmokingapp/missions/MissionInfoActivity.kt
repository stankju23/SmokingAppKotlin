package com.example.stanislavcavajda.bakalarkasmokingapp.missions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.TypedValue
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityMissionInfoBinding
import java.util.Timer

class MissionInfoActivity : AppCompatActivity() {

    var missionPosition = 0

    var timer = Timer()

    var binding:ActivityMissionInfoBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this,Data.actualTheme)

        binding = ActivityMissionInfoBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        this.missionPosition = Data.actualClickedMission

        var viewModel = ActivityListViewModel(Data.missionList[missionPosition].activities,this)

        binding?.activityRecyclerView?.layoutManager = LinearLayoutManager(this)

        binding?.viewModel = viewModel
        binding?.missionInfo = Data.missionList[missionPosition]


        binding?.button5?.setOnClickListener {
            var intent = Intent(this,ObjectivesActivity::class.java)
            intent.putExtra(Constants.extras.EXTRA_ITEM_ID,this.missionPosition)
            startActivity(intent)
        }

        Data.missionList[missionPosition].getDone()
        binding?.numberOfDone?.setText("${resources.getString(R.string.objectives)} ${Data.missionList[missionPosition].done}/5")

        binding?.let {
            if (Data.missionList[missionPosition].done == Data.numberOfObjectivesInCravings) {
                setLayoutBackground(it.missionInfoLayout,this,true)
            } else {
                setLayoutBackground(it.missionInfoLayout,this,false)
            }
        }

        setSupportActionBar(binding?.missionInfoToolbar)
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
        binding?.numberOfDone?.setText("${resources.getString(R.string.objectives)} ${Data.missionList[missionPosition].done}/${Data.numberOfObjectivesInCravings}")

        binding?.let {
            if (Data.missionList[missionPosition].done == Data.numberOfObjectivesInCravings) {
                setLayoutBackground(it.missionInfoLayout,this,true)
            } else {
                setLayoutBackground(it.missionInfoLayout,this,false)
            }
        }


        super.onResume()
    }

    fun setLayoutBackground(layout: ConstraintLayout, context: Context, isDone:Boolean) {
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