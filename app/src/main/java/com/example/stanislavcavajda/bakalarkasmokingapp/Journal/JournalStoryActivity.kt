package com.example.stanislavcavajda.bakalarkasmokingapp.Journal

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityJournalStoryBinding
import kotlinx.android.synthetic.main.activity_journal_story.*

class JournalStoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this,Data.actualTheme)
        setFullScreen()

        var binding:ActivityJournalStoryBinding = DataBindingUtil.setContentView(this,R.layout.activity_journal_story)

        var journalList = ArrayList<Journal>()

        for (item in Data.journalCardSList) {
            if (item.locked.get() != true && item.written.get() == true) {
                journalList.add(item)
            }
        }

        var viewPager = findViewById<ViewPager>(R.id.story_view_pager)
        viewPager.offscreenPageLimit = journalList.size
        viewPager.setPageTransformer(true,com.eftimoff.viewpagertransformers.StackTransformer())
        dots.setupWithViewPager(viewPager)

        binding.viewModel = JournalListViewModel(journalList,this)


        cancel_button.setOnClickListener {
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
