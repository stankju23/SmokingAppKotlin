package com.example.stanislavcavajda.bakalarkasmokingapp.dashboard.mainprogress

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import android.view.View
import com.example.stanislavcavajda.bakalarkasmokingapp.BR
import com.example.stanislavcavajda.bakalarkasmokingapp.model.Date
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Created by stanislavcavajda on 17/12/2017.
 */
class MainProgressViewModel: BaseObservable {

    var date: ObservableField<Date> = ObservableField()
    var progressList: ObservableArrayList<MainProgressItemViewModel> = ObservableArrayList()
    var itemBinding : ItemBinding<MainProgressItemViewModel> = ItemBinding.of(BR.viewModel, R.layout.main_progress_item)
    var context: Context
    var timeList: ObservableArrayList<MainProgressDetailTime> = ObservableArrayList()
    var detailItemBinding : ItemBinding<MainProgressDetailTime> = ItemBinding.of(BR.time, R.layout.main_progress_detail_item)


    constructor(date: Date, context: Context,list: ArrayList<MainProgressDetailTime>) {
        for (i in 0..20){
            progressList.add(MainProgressItemViewModel(false,false))
        }
        setProgress(date)
        updateDetail(list)
        this.context = context
    }

    fun setProgress(date: Date) {
        this.date.set(date)
        for (i in 0..20) {
            if (i < date.days) {
                progressList.get(i).full.set(true)
                if (i.toLong() == date.days - 1) {
                    if (progressList.get(i).last.get() != true) {
                        progressList.get(i).last.set(true)
                        //Log.i("Blinking", "yes")
                    }
                } else {
                    progressList.get(i).last.set(false)
                }
            } else {
                progressList.get(i).full.set(false)
            }
        }
    }

    fun updateDetail(list: ArrayList<MainProgressDetailTime>) {
        timeList.clear()
        timeList.addAll(list)
    }

    fun showDetail(v:View) {
        var detailActivity = Intent(context,MainProgressDetailActivity::class.java)
        (context as Activity).startActivity(detailActivity)
    }


}