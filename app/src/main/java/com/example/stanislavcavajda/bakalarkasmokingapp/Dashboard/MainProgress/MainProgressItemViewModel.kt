package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.MainProgress

import android.databinding.BaseObservable
import android.databinding.ObservableBoolean

/**
 * Created by stanislavcavajda on 28/02/2018.
 */
class MainProgressItemViewModel: BaseObservable {

    var full: ObservableBoolean = ObservableBoolean()

    constructor(full: Boolean) {
        this.full.set(full)
    }
}
