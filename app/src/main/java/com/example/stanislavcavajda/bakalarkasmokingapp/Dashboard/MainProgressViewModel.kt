package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard

import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import com.example.stanislavcavajda.bakalarkasmokingapp.BR
import com.example.stanislavcavajda.bakalarkasmokingapp.Model.Date
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Created by stanislavcavajda on 17/12/2017.
 */
class MainProgressViewModel: BaseObservable {

    var date: ObservableField<Date> = ObservableField()
    var progressList: ObservableArrayList<MainProgressItemViewModel> = ObservableArrayList()
    var itemBinding : ItemBinding<MainProgressItemViewModel> = ItemBinding.of(BR.viewModel, R.layout.main_progress_item)


    constructor(date: Date) {
        for (i in 0..20){
            progressList.add(MainProgressItemViewModel(false))
        }
        setProgress(date)
    }

    fun setProgress(date: Date) {
        this.date.set(date)
        for (i in 0..20) {
            if (i < date.days) {
                progressList.get(i).full.set(true)
            } else {
                progressList.get(i).full.set(false)
            }
        }
    }

}