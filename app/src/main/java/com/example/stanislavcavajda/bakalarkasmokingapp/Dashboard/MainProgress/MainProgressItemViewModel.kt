package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.MainProgress

import android.databinding.BaseObservable
import android.databinding.ObservableBoolean

/**
 * Created by stanislavcavajda on 28/02/2018.
 */
class MainProgressItemViewModel: BaseObservable {

    var full: ObservableBoolean = ObservableBoolean()
    var last: ObservableBoolean = ObservableBoolean()

    constructor(full: Boolean,last:Boolean) {
        this.full.set(full)
        this.last.set(last)
    }
}
