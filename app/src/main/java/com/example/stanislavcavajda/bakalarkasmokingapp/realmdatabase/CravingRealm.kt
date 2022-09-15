package com.example.stanislavcavajda.bakalarkasmokingapp.realmdatabase

import io.realm.RealmObject

/**
 * Created by stanislavcavajda on 12/05/2018.
 */
open class CravingRealm (

        var id:String = "",
        var time:String = "",
        var date:String = "",
        var latitude:Double = 0.0,
        var longitude:Double = 0.0,
        var isHeader:Boolean = false,
        var blacBG:Boolean = false,
        var timeStamp:Long = 0

):RealmObject()