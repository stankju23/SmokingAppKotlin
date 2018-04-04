package com.example.stanislavcavajda.bakalarkasmokingapp.Missions

import android.databinding.BaseObservable
import android.databinding.ObservableBoolean
import android.view.View

/**
 * Created by stanislavcavajda on 23/03/2018.
 */
class Activity:BaseObservable {

    var isDone:ObservableBoolean = ObservableBoolean()
    var initDone:Boolean = false
    var objective:Objective? = null

    constructor(done: Boolean, objective: Objective) {
        this.isDone.set(done)
        this.objective = objective
    }

    fun setDone(v:View) {
        if (isDone.get()) {
            isDone.set(false)
        } else {
            isDone.set(true)
        }
    }
}