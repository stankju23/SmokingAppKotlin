package com.example.stanislavcavajda.bakalarkasmokingapp.dashboard.healthlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityHealthProgressListBinding

class HealthProgressListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this,Data.actualTheme)

        var binding = ActivityHealthProgressListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var viewModel = HealthProgressListViewModel(Data.healthProgressViewList, this)

        binding.viewModel = viewModel

        var recycler = findViewById<RecyclerView>(R.id.health_list_recycler)
        recycler.layoutManager =
            LinearLayoutManager(this)

        setSupportActionBar(binding.productsToolbar)

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
