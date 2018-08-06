package com.example.stanislavcavajda.bakalarkasmokingapp.Missions

import android.content.Context
import android.content.Intent
import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.example.stanislavcavajda.bakalarkasmokingapp.BR
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.OnClick
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Created by stanislavcavajda on 23/03/2018.
 */
class ActivityListViewModel :BaseObservable {

    lateinit var context:Context
    var activityList:ObservableArrayList<Activity> = ObservableArrayList()
    var itemBinding : ItemBinding<Activity> = ItemBinding.of(BR.activity, R.layout.activity_list_item)
    var itemBinding1 : ItemBinding<Activity> = ItemBinding.of(BR.activity, R.layout.objectives_item)

    var listener = object : OnClick {

        override fun OnItemClick(v:View) {
            var recyclerView: RecyclerView = (context as android.app.Activity).findViewById(R.id.activity_recycler_view)
            var clickedPosition = recyclerView.getChildAdapterPosition(v)
            Data.actualClickedActivity = clickedPosition

            Log.i("item","clicked at position $clickedPosition")
            var objectivesInfo = Intent(context, ObjectivesActivity::class.java)
            (context as android.app.Activity).startActivity(objectivesInfo)

        }
    }
    constructor(activities: ArrayList<Activity>,context: Context) {
        this.context = context
        itemBinding.bindExtra(BR.activity_listener,listener)
        activityList.addAll(activities)
    }

}