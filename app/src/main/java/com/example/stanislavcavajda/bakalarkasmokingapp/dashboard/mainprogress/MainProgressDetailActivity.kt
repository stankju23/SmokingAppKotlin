package com.example.stanislavcavajda.bakalarkasmokingapp.dashboard.mainprogress

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.model.Date
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityMainProgressDetailBinding
import java.util.Timer
import kotlin.concurrent.timerTask

class MainProgressDetailActivity : AppCompatActivity() {

    var timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this,Data.actualTheme)

        var binding: ActivityMainProgressDetailBinding = ActivityMainProgressDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var viewModel = MainProgressViewModel(Date(0,0),this, Data.timeList)

        binding.mainProgressDetailRecyclerView.itemAnimator = null

        setSupportActionBar(binding.mainProgressDetailToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)
        supportActionBar?.title = getString(R.string.main_progress_detail_title)

        timer.scheduleAtFixedRate(timerTask {

            runOnUiThread {
                if (Data.timeList.size != 0) {
                    viewModel.updateDetail(Data.timeList)
                }
                Log.i("Detail", "updated")
            }

        },0,1000)

        binding.viewModel = viewModel


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
        timer.purge()
        super.onDestroy()
    }
    override fun onPause() {

        super.onPause()
    }

    override fun onBackPressed() {
        this.finish()
        super.onBackPressed()
    }

}
