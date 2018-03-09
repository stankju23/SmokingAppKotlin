package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager

import android.content.Context
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableFloat
import android.graphics.drawable.Drawable
import android.view.View
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
    var canBuy: ObservableBoolean = ObservableBoolean()
    var isBought: ObservableBoolean = ObservableBoolean()
    var endDate:ObservableField<String> = ObservableField()
    var progress: ObservableFloat = ObservableFloat()
    var context:Context

    constructor(id: Int,title: String, desc: String, price: Int, image: Drawable, bought: Boolean,context: Context) {
        setWish(id,title,desc,price,image)
        this.isBought.set(bought)
        this.context = context
    }

    fun setWish(id:Int,title: String, desc: String, price: Int, image: Drawable) {
        this.id = id
        this.title = title
        this.desc = desc
        this.price = price
        this.image = image

        if (!isBought.get()) {
            if (price <= Data.MoneyDashboard.actualMoneyState) {
                this.canBuy.set(true)
                this.endDate.set("")
                this.progress.set(100f)
            } else {
                val dateConverter = DateConverter()
                var currentTimestamp = dateConverter.getCurrentTimestamp()
                var moneySavedPerDay = (Data.MoneyDashboard.packagePrice / Data.MoneyDashboard.cigarretesInPackage) * Data.MoneyDashboard.cigarretesPerDay
                var day = 86400
                this.endDate.set(formatDate(dateConverter.getDate((((price.toFloat() - Data.MoneyDashboard.actualMoneyState) / moneySavedPerDay.toFloat()) * day + currentTimestamp).toLong())))
                this.progress.set((Data.MoneyDashboard.actualMoneyState / price.toFloat())*100)
                this.canBuy.set(false)
            }
        }


    }

    fun setBought(v:View) {
        if (canBuy.get()) {
            if (!this.isBought.get()) {
                this.isBought.set(true)
                Data.MoneyDashboard.moneySpend += this.price
            } else {
                this.isBought.set(false)
                Data.MoneyDashboard.moneySpend -= this.price
            }
        }

//        var swipe = (context as Activity).findViewById<SwipeLayout>(R.id.wish_item_swipe_layout)
    }

    fun formatDate(date:String):String {
        val day = date.substring(0,2)
        val month = date.substring(3,5)
        val year = date.substring(6, date.length)
        return "$day.$month.$year"
    }
}