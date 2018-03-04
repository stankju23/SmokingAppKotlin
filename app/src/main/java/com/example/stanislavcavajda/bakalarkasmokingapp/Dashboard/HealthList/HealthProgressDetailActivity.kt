package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.HealthList

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityHealthProgressDetailBinding
import com.github.bluzwong.swipeback.SwipeBackActivityHelper
import kotlinx.android.synthetic.main.activity_health_progress_detail.*

class HealthProgressDetailActivity : AppCompatActivity() {

    var helper = SwipeBackActivityHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityHealthProgressDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_health_progress_detail)
        var itemId = intent.getIntExtra(Constants.extras.EXTRA_ITEM_ID,0)
        var healthProgres = Data.healthProgressViewList[itemId]

        setSupportActionBar(health_progress_detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_material)
        binding.viewModel = healthProgres

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                super.onBackPressed()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}
