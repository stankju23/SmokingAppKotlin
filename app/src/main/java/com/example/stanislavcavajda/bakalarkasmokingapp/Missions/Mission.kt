package com.example.stanislavcavajda.bakalarkasmokingapp.Missions

import android.content.Context
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Model.Date
import com.example.stanislavcavajda.bakalarkasmokingapp.R

/**
 * Created by stanislavcavajda on 21/03/2018.
 */
class Mission {

        var id:String
        var name: String
        var date:String
        var completionDate:Date
        var locked:ObservableBoolean = ObservableBoolean()
        var available:ObservableBoolean = ObservableBoolean()
        var activities: ArrayList<Activity> = ArrayList()
        var done:Int = 0
        var observableCompletionDate:ObservableField<Date> = ObservableField()
        var completionTime:ObservableField<String> = ObservableField()
        var context:Context



    constructor(id:String,name:String,date:String,completionDate: Date,locked:Boolean,available:Boolean,activities:ArrayList<Activity>,done:Int,context: Context){
        this.id = id
        this.name = name
        this.date = date
        this.completionDate = completionDate
        this.locked.set(locked)
        this.available.set(available)
        this.activities?.addAll(activities)
        this.context = context
        completionTime.set("${completionDate?.hours} ${context.resources.getString(R.string.hours)}  ${completionDate?.minutes} ${context.resources.getString(R.string.minutes)}  ${completionDate?.seconds} ${context.resources.getString(R.string.seconds)}")
        this.done = done
    }



    fun getDone() {
        this.done = 0
        for (i in 0..activities?.size!! - 1) {
            if (activities?.get(i)?.isDone?.get()!!) {
               this.done++
            }
        }

        completionTime.set("${completionDate?.hours} ${context.resources.getString(R.string.hours)}  ${completionDate?.minutes} ${context.resources.getString(R.string.minutes)}  ${completionDate?.seconds} ${context.resources.getString(R.string.seconds)}")
    }

    fun getDoneToString():String {
        return "${this.done}/${Data.numberOfObjectivesInCravings}"
    }

    fun setMission(mission: Mission) {
        this.name = mission.name
        this.date = mission.date
        this.completionDate = mission.completionDate
        this.locked.set(mission.locked.get())
        this.available.set(mission.available.get())
        this.activities = mission.activities
        this.done = mission.done
    }
}