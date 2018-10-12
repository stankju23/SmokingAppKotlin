package com.example.stanislavcavajda.bakalarkasmokingapp.Cravings

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.brandongogetap.stickyheaders.StickyLayoutManager
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import kotlinx.android.synthetic.main.activity_show_cravings.*

class ShowCravings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        ThemeManager.setTheme(this,Data.actualTheme)

        setContentView(R.layout.activity_show_cravings)

        var toolbar = findViewById<Toolbar>(R.id.show_cravings_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)
        supportActionBar?.title = resources.getString(R.string.cravings_title)


        var adapter = CravingsAdapter(Data.cravings,this)
        var layoutManager = StickyLayoutManager(this,adapter)
        layoutManager.elevateHeaders(true)
        show_cravings_recyclerView.layoutManager = layoutManager
        show_cravings_recyclerView.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
