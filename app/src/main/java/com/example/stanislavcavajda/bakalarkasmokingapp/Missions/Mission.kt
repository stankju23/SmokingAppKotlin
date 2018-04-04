package com.example.stanislavcavajda.bakalarkasmokingapp.Missions

/**
 * Created by stanislavcavajda on 21/03/2018.
 */
class Mission(var name: String = "",var date:String = "",var locked:Boolean = false,var available:Boolean = false,var activities: ArrayList<Activity>? = null,var done:Int = 0  ){

    fun getDone() {
        this.done = 0
        for (i in 0..activities?.size!! - 1) {
            if (activities?.get(i)?.isDone?.get()!!) {
               this. done ++
            }
        }
    }

    fun getDoneToString():String {
        return "${this.done}/5"
    }

    fun setMission(mission: Mission) {
        this.name = mission.name
        this.date = mission.date
        this.locked = mission.locked
        this.available = mission.available
        this.activities = mission.activities
        this.done = mission.done
    }
}