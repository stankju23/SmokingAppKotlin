package com.example.stanislavcavajda.bakalarkasmokingapp.Cravings

import android.content.Context
import android.location.Address
import android.location.Geocoder
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by stanislavcavajda on 12/05/2018.
 */
class Craving {

    var id:String
    var time:String
    var date:String
    var latitude:Double
    var longitude:Double
    var city:String
    var address:String
    var context:Context

    constructor(id:String,time:String,date:String,lat: Double,long: Double,context: Context) {
        this.id = id
        this.time = time
        this.date = date
        this.latitude = lat
        this.longitude = long
        this.context = context
        this.city = getAddress(this.latitude,this.longitude)[0]
        this.address = "${getAddress(this.latitude,this.longitude)[1]},${getAddress(this.latitude,this.longitude)[2]}"

    }

    fun getAddress(lat:Double,long:Double):ArrayList<String> {
        var geocoder = Geocoder(context, Locale.getDefault())
        var list = ArrayList<Address>()
        list.addAll(geocoder.getFromLocation(lat,long,1))
        var city:ArrayList<String> = ArrayList()
        try {
            city = arrayListOf(list.get(0).locality,list.get(0).thoroughfare,list.get(0).featureName)
        } catch (e:Exception) {

        }

        return city
    }

}