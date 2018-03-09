package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.MainProgress

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Model.Date
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityMainProgressDetailBinding
import kotlinx.android.synthetic.main.activity_main_progress_detail.*
import java.util.Timer
import kotlin.concurrent.timerTask

class MainProgressDetailActivity : AppCompatActivity() {

    var timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding: ActivityMainProgressDetailBinding = DataBindingUtil.setContentView(this,R.layout.activity_main_progress_detail)
        var viewModel = MainProgressViewModel(Date(0,0),this, Data.timeList)

        main_progress_detail_recycler_view.itemAnimator = null

        timer.scheduleAtFixedRate(timerTask {

            runOnUiThread {
                if (Data.timeList.size != 0) {
                    viewModel.updateDetail(Data.timeList)
                }
            }

        },0,1000)

        binding.viewModel = viewModel

    }

    override fun onPause() {
        timer.cancel()
        timer.purge()
        super.onPause()
    }

    override fun onBackPressed() {
        this.finish()
        super.onBackPressed()
    }

}
