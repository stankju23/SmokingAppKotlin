package com.example.stanislavcavajda.bakalarkasmokingapp.RealmDatabase

import io.realm.RealmObject

open class ActivityRealm(

    var id:String = "",
    var isDone:Boolean = false,
    var objective :ObjectiveRealm? = null

) : RealmObject()