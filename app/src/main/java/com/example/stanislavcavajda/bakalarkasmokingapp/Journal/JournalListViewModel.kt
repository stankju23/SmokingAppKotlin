package com.example.stanislavcavajda.bakalarkasmokingapp.Journal

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.stanislavcavajda.bakalarkasmokingapp.BR
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Animate
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.OnClick
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import me.tatarka.bindingcollectionadapter2.ItemBinding

class JournalListViewModel: BaseObservable {

    var journalItemList: ObservableArrayList<Journal> = ObservableArrayList()
    var itemBinding:ItemBinding<Journal> = ItemBinding.of(BR.journal,R.layout.journal_card)
    var itemBinding1:ItemBinding<Journal> = ItemBinding.of(BR.journalItem,R.layout.journal_story_item)
    lateinit var context:Context

    var listener = object : OnClick {
        override fun OnItemClick(v: View) {
            var recyclerView: RecyclerView = (context as Activity).findViewById(R.id.journal_recycler_view)
            var clickedPosition = recyclerView.getChildAdapterPosition(v)
            Data.actualClickedMission = clickedPosition
            var editJournal = Intent(context, EditJournal::class.java)
            if (!Data.journalCardSList[clickedPosition].locked.get()) {
                editJournal.putExtra("position",clickedPosition)
                (context as Activity).startActivity(editJournal)
            } else {
                Animate.shakeAnim(v,context)
            }
        }

    }

    constructor(list:ArrayList<Journal>,context: Context) {
        itemBinding.bindExtra(BR.journalListener,listener)
        this.journalItemList.addAll(list)
        this.context = context
    }

    fun showStory(v:View) {
        var intent = Intent(context,JournalStoryActivity::class.java)
        context.startActivity(intent)
    }
}