package com.example.stanislavcavajda.bakalarkasmokingapp.Cravings

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.util.Log
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import java.util.Locale

/**
 * Created by stanislavcavajda on 12/05/2018.
 */
class Craving:CravingItem,ClusterItem {


    var id:String
    var time:String
    var date:String
    var latitude:Double
    var longitude:Double
    var city:String
    var address:String
    var context:Context
    var blackBG:Boolean

    constructor(id:String,time:String,date:String,lat: Double,long: Double,context: Context,blackBG:Boolean) {
        this.id = id
        this.time = time
        this.date = date
        this.latitude = lat
        this.longitude = long
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
        var list = ArrayList<Address>()
        list.addAll(geocoder.getFromLocation(lat,long,1))
        var city:ArrayList<String> = ArrayList()
        try {
            city = arrayListOf(list.get(0).locality,list.get(0).thoroughfare,list.get(0).featureName)
        } catch (e:Exception) {

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