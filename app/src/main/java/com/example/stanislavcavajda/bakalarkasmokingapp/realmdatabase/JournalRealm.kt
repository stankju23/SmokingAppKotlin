package com.example.stanislavcavajda.bakalarkasmokingapp.realmdatabase

import io.realm.RealmObject

open class JournalRealm (
    var id:String = "",
    var title:String = "",
    var desc:String = "",
    var timestamp:Long = 0L
) :RealmObject()