package com.example.stanislavcavajda.bakalarkasmokingapp.RealmDatabase

import io.realm.RealmObject

open class ObjectiveRealm(
    var id:String = "",
    var title:String = "",
    var desc:String = ""
) : RealmObject()