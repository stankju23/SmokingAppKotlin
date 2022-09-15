package com.example.stanislavcavajda.bakalarkasmokingapp.realmdatabase

import io.realm.RealmObject

open class ActivityRealm(

    var id:String = "",
    var isDone:Boolean = false,
    var objective :ObjectiveRealm? = null

) : RealmObject()