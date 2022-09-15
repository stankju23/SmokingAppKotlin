package com.example.stanislavcavajda.bakalarkasmokingapp.cravings

import android.content.Context
import android.location.Geocoder
import android.util.Log
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import java.util.Locale

/**
 * Created by stanislavcavajda on 12/05/2018.
 */
class Craving:CravingItem, ClusterItem {


    var id:String
    var time:String
    var date:String
    var latitude:Double
    var longitude:Double
    var city:String
    var address:String
    var context:Context
    var blackBG:Boolean
    var timeStamp:Long

    constructor(id:String,time:String,date:String,lat: Double,long: Double,context: Context,blackBG:Boolean,timestamp: Long) {
        this.id = id
        this.time = time
        this.date = date
        this.latitude = lat
        this.longitude = long
        this.timeStamp = timestamp
        this.context = context
        if (this.latitude != 0.0 && this.longitude != 0.0) {
            try {
                this.city = getAddress(this.latitude, this.longitude)[0]
                this.address = "${getAddress(this.latitude, this.longitude)[1]},${getAddress(this.latitude, this.longitude)[2]}"

            } catch (e:Exception) {
                Log.i("Cant get","Address")
                this.city = context.getString(R.string.not_specified_place)
                this.address = context.getString(R.string.not_specified_place)
            }

        } else {
            this.city = context.getString(R.string.not_specified_place)
            this.address = context.getString(R.string.not_specified_place)
        }
        this.blackBG = blackBG

    }

    fun getAddress(lat:Double,long:Double):ArrayList<String> {
        var geocoder = Geocoder(context, Locale.getDefault())
        var list = geocoder.getFromLocation(lat,long,1)
        var city:ArrayList<String> = ArrayList()
        list?.get(0)?.let {
            try {
                city = arrayListOf(it.locality,it.thoroughfare,it.featureName)
            } catch (e:Exception) {

            }

        }
        return city
    }

    override fun getSnippet(): String {
        return "${this.date}, ${this.time}"
    }

    override fun getTitle(): String {
        return this.city
    }

    override fun getPosition(): LatLng {
        return LatLng(this.latitude,this.longitude)
    }

}