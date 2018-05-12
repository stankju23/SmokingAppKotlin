package com.example.stanislavcavajda.bakalarkasmokingapp.Cravings

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.jaychang.srv.decoration.SimpleSectionHeaderProvider
import kotlinx.android.synthetic.main.activity_show_cravings.*
import kotlinx.android.synthetic.main.craving_header.view.*

class ShowCravings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        ThemeManager.setTheme(this,Data.actualTheme)

        setContentView(R.layout.activity_show_cravings)

        var toolbar = findViewById<Toolbar>(R.id.show_cravings_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_material)
        supportActionBar?.title = resources.getString(R.string.cravings_title)

        addRecyclerHeaders()
        bindData()

    }

    fun addRecyclerHeaders() {
        var sectionHeader = object :SimpleSectionHeaderProvider<Craving>(){


            override fun getSectionHeaderView(craving: Craving, p1: Int): View {
                var view = LayoutInflater.from(this@ShowCravings).inflate(R.layout.craving_header,null,false)
                view.craving_date.setText(craving.date)
                return view
            }

            override fun isSameSection(craving: Craving, nextCraving: Craving): Boolean {
                return craving.date == nextCraving.date
            }

            override fun isSticky(): Boolean {
                return true
            }
        }

        recyclerView.setSectionHeader(sectionHeader)
    }

    fun bindData() {
        var list = ArrayList<CravingCell>()
        for (i in 0..5) {
            var craving = Craving("1","14:28","10.05.2018",49.308632,18.788138,this)
            list.add(CravingCell(craving))
        }

        for (i in 0..10) {
            var craving = Craving("1","09:18","11.05.2018",49.308632,18.788138,this)
            list.add(CravingCell(craving))
        }

        for (i in 0..12) {
            var craving = Craving("1","10:34","12.05.2018",49.308632,18.788138,this)
            list.add(CravingCell(craving))
        }

        recyclerView.addCells(list)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.show_map -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.show_cravings, menu)
        return true
    }

}
