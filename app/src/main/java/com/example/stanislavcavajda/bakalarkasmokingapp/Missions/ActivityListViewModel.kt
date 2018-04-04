package com.example.stanislavcavajda.bakalarkasmokingapp.Missions

import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import com.example.stanislavcavajda.bakalarkasmokingapp.BR
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Created by stanislavcavajda on 23/03/2018.
 */
class ActivityListViewModel :BaseObservable {

    var activityList:ObservableArrayList<Activity> = ObservableArrayList()
    var itemBinding : ItemBinding<Activity> = ItemBinding.of(BR.activity, R.layout.activity_list_item)
    var itemBinding1 : ItemBinding<Activity> = ItemBinding.of(BR.activity, R.layout.objectives_item)

    constructor(activities: ArrayList<Activity>) {
        activityList.addAll(activities)
    }

}