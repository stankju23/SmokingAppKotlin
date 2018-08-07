package com.example.stanislavcavajda.bakalarkasmokingapp.RealmDatabase

import io.realm.RealmObject

open class CardRealm (

    var id:String = "",
    var title:String = "",
    var category:Int = 0,
    var desc:String = "",
    var popularity:Int = 0

) :RealmObject()