package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard

import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.MainProgress.MainProgressDetailTime

/**
 * Created by stanislavcavajda on 09/03/2018.
 */
open class ChangeListener(list: ArrayList<MainProgressDetailTime>) {

    var list:ArrayList<MainProgressDetailTime> = ArrayList()

    var l: listener? = null

    init {
        this.list = list
    }

    interface listener {
        fun onChange(list: ArrayList<MainProgressDetailTime>)
    }

    fun setChangeListener(mListener: listener) {
        l = mListener
    }

    fun somethingChanged() {
        if (l != null) {
            l!!.onChange(this.list)
        }
    }
}