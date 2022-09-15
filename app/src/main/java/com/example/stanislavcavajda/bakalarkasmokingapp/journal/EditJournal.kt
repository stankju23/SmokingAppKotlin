package com.example.stanislavcavajda.bakalarkasmokingapp.journal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityEditJournalBinding

class EditJournal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this,Data.actualTheme)

        val binding = ActivityEditJournalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var position = intent.getIntExtra("position",0)

        var toolbar = findViewById<Toolbar>(R.id.edit_journal_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)
        supportActionBar?.title = "${resources.getString(R.string.story_title)}${Data.journalCardSList[position].id}"



        binding.journalTitleText.setText(Data.journalCardSList[position].title.get())
        binding.journalDescText.setText(Data.journalCardSList[position].desc.get())


        binding.saveJournal.setOnClickListener {
            Data.journalCardSList[position].updateJournalInfo(binding.journalTitleText.text.toString(),binding.journalDescText.text.toString())
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
