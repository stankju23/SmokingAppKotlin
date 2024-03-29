package com.example.stanislavcavajda.bakalarkasmokingapp.Missions

import android.databinding.BaseObservable
import android.databinding.ObservableBoolean
import android.view.View
import com.example.stanislavcavajda.bakalarkasmokingapp.RealmDatabase.RealmDB

/**
 * Created by stanislavcavajda on 23/03/2018.
 */
class Activity:BaseObservable {


    var id:String
    var isDone:ObservableBoolean = ObservableBoolean()
    var objective:Objective? = null

    constructor(done: Boolean, objective: Objective,id:String) {
        this.isDone.set(done)
        this.objective = objective
        this.id = id
    }

    fun setDone(v:View) {
        if (isDone.get()) {
            isDone.set(false)
        } else {
            isDone.set(true)
        }
        RealmDB.updateMission(this)
    }

    fun onClick(v:View) {

    }

}