package com.example.stanislavcavajda.bakalarkasmokingapp.RealmDatabase

import io.realm.RealmObject

/**
 * Created by stanislavcavajda on 13/03/2018.
 */
open class WishRealm(

    var id:String = "",
    var title: String = "",
    var desc: String = "",
    var price: Int = 0,
    var image: String = "",
    var canBuy: Boolean = false,
    var isBought: Boolean = false,
    var endDate:String = "",
    var progress: Float = 0f

) : RealmObject()