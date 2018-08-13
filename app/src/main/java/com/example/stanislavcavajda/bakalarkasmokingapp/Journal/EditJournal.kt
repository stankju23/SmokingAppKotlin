package com.example.stanislavcavajda.bakalarkasmokingapp.Journal

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import kotlinx.android.synthetic.main.activity_edit_journal.*

class EditJournal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this,Data.actualTheme)

        setContentView(R.layout.activity_edit_journal)

        var position = intent.getIntExtra("position",0)

        var toolbar = findViewById<Toolbar>(R.id.edit_journal_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)
        supportActionBar?.title = "${resources.getString(R.string.story_title)}${Data.journalCardSList[position].id}"



        journal_title_text.setText(Data.journalCardSList[position].title.get())
        journal_desc_text.setText(Data.journalCardSList[position].desc.get())


        save_journal.setOnClickListener {
            Data.journalCardSList[position].updateJournalInfo(journal_title_text.text.toString(),journal_desc_text.text.toString())
            onBackPressed()
        }

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
}
