package com.example.stanislavcavajda.bakalarkasmokingapp.RealmDatabase

import io.realm.RealmObject

open class ObjectiveRealm(
    var id:String = "",
    var title:Int = 0,
    var desc:Int = 0
) : RealmObject()