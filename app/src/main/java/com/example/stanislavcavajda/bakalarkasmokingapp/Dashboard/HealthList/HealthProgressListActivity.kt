package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.HealthList

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityHealthProgressListBinding
import com.github.bluzwong.swipeback.SwipeBackActivityHelper
import kotlinx.android.synthetic.main.activity_health_progress_list.*

class HealthProgressListActivity : AppCompatActivity() {

    var helper = SwipeBackActivityHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this,Data.actualTheme)

        var binding = DataBindingUtil.setContentView<ActivityHealthProgressListBinding>(this, R.layout.activity_health_progress_list)


        var viewModel = HealthProgressListViewModel(Data.healthProgressViewList, this)

        binding.viewModel = viewModel

        var recycler = findViewById<RecyclerView>(R.id.health_list_recycler)
        recycler.layoutManager = LinearLayoutManager(this)

        setSupportActionBar(products_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)
        supportActionBar?.title = resources.getString(R.string.health_progress_title)

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

    override fun onBackPressed() {
        this.finish()
        super.onBackPressed()
    }
}
