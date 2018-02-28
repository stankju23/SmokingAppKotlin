package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import com.example.stanislavcavajda.bakalarkasmokingapp.BR
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import me.tatarka.bindingcollectionadapter2.OnItemBind

/**
 * Created by stanislavcavajda on 17/12/2017.
 */
class DashboardListViewModel :BaseObservable {

    var list: ObservableArrayList<Object> = ObservableArrayList<Object>()
    lateinit var itemBinding: OnItemBind<Object>

    constructor(list: ArrayList<Object>, context: Context){
        this.list.addAll(list)
        itemBinding = OnItemBind { itemBinding, position, item -> itemBinding.set(BR.viewModel,

                when(position) {
                    Constants.viewTypes.MAIN_PROGRESS_VIEW_TYPE -> R.layout.main_progress_item
                    Constants.viewTypes.HEALTH_PROGRESS_VIEW_TYPE -> R.layout.health_progress_dashboard_item
                    else -> R.layout.dashboard_main_progress
                }

        )}
    }
}