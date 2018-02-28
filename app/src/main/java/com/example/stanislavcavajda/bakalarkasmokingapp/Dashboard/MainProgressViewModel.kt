package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard

import android.databinding.BaseObservable
import android.databinding.ObservableField
import android.databinding.ObservableFloat
import com.example.stanislavcavajda.bakalarkasmokingapp.Model.Date

/**
 * Created by stanislavcavajda on 17/12/2017.
 */
class MainProgressViewModel: BaseObservable {

    var date: ObservableField<Date> = ObservableField()
    var progress: ObservableFloat = ObservableFloat()

    constructor(progress: Float,date: Date){
      setProgress(progress,date)
    }

    fun setProgress(progress: Float, date: Date) {
        this.progress.set(progress)
        this.date.set(date)
    }

}