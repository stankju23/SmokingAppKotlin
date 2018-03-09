package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager

import android.content.Context
import android.graphics.drawable.Drawable
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.DateConverter

/**
 * Created by stanislavcavajda on 04/03/2018.
 */

class Wish {

    var id: Int = 0
    var title: String = ""
    var desc: String = ""
    var price: Int = 0
    var image: Drawable? = null
    var canBuy: Boolean = false
    var isBought: Boolean = false
    var endDate: String = ""
    var progress: Float = 0.0f
    var context:Context

    constructor(id: Int,title: String, desc: String, price: Int, image: Drawable, bought: Boolean,context: Context) {
        setWish(id,title,desc,price,image,bought)
        this.context = context
    }

    fun setWish(id:Int,title: String, desc: String, price: Int, image: Drawable, bought: Boolean) {
        this.id = id
        this.title = title
        this.desc = desc
        this.price = price
        this.image = image
        this.canBuy = canBuy
        this.isBought = bought


        if (price <= Data.MoneyDashboard.moneySaved) {
            this.canBuy = true
            this.endDate = ""
            this.progress = 100f
        } else {
            val dateConverter = DateConverter()
            var currentTimestamp = dateConverter.getCurrentTimestamp()
            var moneySavedPerDay = (Data.MoneyDashboard.packagePrice / Data.MoneyDashboard.cigarretesInPackage) * Data.MoneyDashboard.cigarretesPerDay
            var day = 86400
            this.endDate = formatDate(dateConverter.getDate((((price.toFloat() - Data.MoneyDashboard.moneySaved) / moneySavedPerDay.toFloat()) * day + currentTimestamp).toLong()))
            this.progress = (1 - ((price.toFloat() - Data.MoneyDashboard.moneySaved) / price.toFloat()))*100
            this.canBuy = false
        }


    }

    fun formatDate(date:String):String {
        val day = date.substring(0,2)
        val month = date.substring(3,5)
        val year = date.substring(6, date.length)
        return "$day.$month.$year"
    }
}