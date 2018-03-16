package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.HealthList

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import android.view.View
import com.example.stanislavcavajda.bakalarkasmokingapp.BR
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Created by stanislavcavajda on 27/02/2018.
 */
class HealthProgressListViewModel : BaseObservable {

    var healthProgressViewList: ObservableArrayList<HealthProgressViewModel> = ObservableArrayList<HealthProgressViewModel>()
    var context: Context? = null
    var dashboardHealthProgressViewList: ObservableArrayList<HealthProgressViewModel> = ObservableArrayList<HealthProgressViewModel>()

    var itemBinding : ItemBinding<HealthProgressViewModel> = ItemBinding.of(BR.viewModel, R.layout.health_progress_list_item)



    constructor(healthProgressViewList: ArrayList<HealthProgressViewModel>, context: Context) {
        this.healthProgressViewList.clear()
        this.healthProgressViewList.addAll(healthProgressViewList)
        this.context = context
        var index = 0
        for (item in this.healthProgressViewList) {
            if (item.date.get().progress < 100 && index < 3) {
                this.dashboardHealthProgressViewList.add(item)
                index++
            }
        }
    }

    fun updateAll() {
        var index = 0
        for (item in this.healthProgressViewList) {
            if (item.date.get().progress < 100 && index < 3) {
                this.dashboardHealthProgressViewList.set(index,item)
                index++
            }
        }
        notifyChange()
    }


    fun findMore(v:View) {
        var healthProgressList = Intent(context, HealthProgressListActivity::class.java)
        healthProgressList.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        (context as Activity).startActivity(healthProgressList)
//        var fragmentManager = (this.context as Activity).fragmentManager
//        var fragmenttransaction = fragmentManager.beginTransaction()
//        fragmenttransaction.setCustomAnimations(R.animator.slide_in_left,R.animator.slide_out_right,R.animator.slide_in_right, R.animator.slide_out_left)
//            .replace(R.id.fragment_container, HealthProgressListFragment())
//            .addToBackStack("healthProgressViewList").commit()

    }



}