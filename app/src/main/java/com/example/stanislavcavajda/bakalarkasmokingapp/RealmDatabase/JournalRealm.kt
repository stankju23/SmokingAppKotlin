package com.example.stanislavcavajda.bakalarkasmokingapp.RealmDatabase

import io.realm.RealmObject

open class JournalRealm (
    var id:String = "",
    var title:String = "",
    var desc:String = "",
    var timestamp:Long = 0L
) :RealmObject()