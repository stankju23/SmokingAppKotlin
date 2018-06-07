package com.example.stanislavcavajda.bakalarkasmokingapp.Cravings

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
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
        if (item?.itemId == R.id.show_map) {
            var mapActivity = Intent(this,CravingMapActivity::class.java)
            startActivity(mapActivity)

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.show_cravings, menu)
        return true
    }

}
