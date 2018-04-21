package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager

import android.content.Context
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableFloat
import android.net.Uri
import android.view.View
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.DateConverter
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.RealmDB

/**
 * Created by stanislavcavajda on 04/03/2018.
 */

class Wish {

    var id: String = ""
    var title: ObservableField<String> = ObservableField()
    var desc: ObservableField<String> = ObservableField()
    var price: Int = 0
    var image: Uri? = null
    var canBuy: ObservableBoolean = ObservableBoolean()
    var isBought: ObservableBoolean = ObservableBoolean()
    var endDate: ObservableField<String> = ObservableField()
    var progress: ObservableFloat = ObservableFloat()
    var context: Context

    constructor(id: String, title: String, desc: String, price: Int, bought: Boolean, context: Context, imageUri: Uri) {

        this.id = id

        this.context = context


        if (bought) {
            this.canBuy.set(true)
            this.endDate.set("")
            this.progress.set(100f)
            this.isBought.set(bought)
            Data.MoneyDashboard.moneySpend += price
        }

        setWish(title, desc, price, imageUri)
    }

    fun setWish(title: String, desc: String, price: Int, imageUri: Uri) {
        this.title.set(title)
        this.desc.set(desc)
        this.price = price
        this.image = imageUri



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
                this.progress.set((Data.MoneyDashboard.actualMoneyState / price.toFloat()) * 100)
                this.canBuy.set(false)
            }
        }
    }

    fun setBought(v: View) {

        if (isBought.get()) {
            buy(true)
        } else {
            buy(false)
        }

        RealmDB.updateWish(this.id, this)
    }

    fun formatDate(date: String): String {
        val day = date.substring(0, 2)
        val month = date.substring(3, 5)
        val year = date.substring(6, date.length)
        return "$day.$month.$year"
    }

    fun buy(bought: Boolean) {
        if (canBuy.get()) {
            if (!bought) {
                this.isBought.set(true)
                Data.MoneyDashboard.moneySpend += this.price
            } else {
                this.isBought.set(false)
                Data.MoneyDashboard.moneySpend -= this.price
            }
        }
    }
}
