package com.example.stanislavcavajda.bakalarkasmokingapp.RealmDatabase

import io.realm.RealmList
import io.realm.RealmObject

open class MissionRealm(

    var id:String = "",
    var name: String = "",
    var date:String = "",
    var locked:Boolean = false,
    var available:Boolean = false,
    var activities: RealmList<ActivityRealm>? = null,
    var done:Int = 0

):RealmObject()