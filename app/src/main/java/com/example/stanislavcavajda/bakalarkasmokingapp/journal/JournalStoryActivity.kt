package com.example.stanislavcavajda.bakalarkasmokingapp.journal

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityJournalStoryBinding

class JournalStoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this,Data.actualTheme)
        setFullScreen()

        var binding:ActivityJournalStoryBinding = ActivityJournalStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var journalList = ArrayList<Journal>()

        for (item in Data.journalCardSList) {
            if (item.locked != true && item.written.get() == true) {
                journalList.add(item)
            }
        }

        var viewPager = findViewById<ViewPager>(R.id.story_view_pager)
        viewPager.offscreenPageLimit = journalList.size
        binding.dots.setupWithViewPager(viewPager)

        binding.viewModel = JournalListViewModel(journalList,this)


        binding.cancelButton.setOnClickListener {
            onBackPressed()
        }
    }

    fun setFullScreen() {
        val decor_View = window.decorView

        val ui_Options = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_FULLSCREEN
            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        decor_View.systemUiVisibility = ui_Options
    }
}
