package com.example.stanislavcavajda.bakalarkasmokingapp.cravings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityShowCravingsBinding

class ShowCravings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        ThemeManager.setTheme(this,Data.actualTheme)

        val binding = ActivityShowCravingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.showCravingsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)
        supportActionBar?.title = resources.getString(R.string.cravings_title)


        var adapter = CravingsAdapter(Data.cravings,this)
        var layoutManager = LinearLayoutManager(this)
        binding.showCravingsRecyclerView.layoutManager = layoutManager
        binding.showCravingsRecyclerView.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
