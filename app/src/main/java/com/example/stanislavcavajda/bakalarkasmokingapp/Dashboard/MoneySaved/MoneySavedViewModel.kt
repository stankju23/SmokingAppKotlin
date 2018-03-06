package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.MoneySaved

import android.databinding.BaseObservable
import android.databinding.ObservableDouble
import android.databinding.ObservableFloat

/**
 * Created by stanislavcavajda on 06/03/2018.
 */
class MoneySavedViewModel: BaseObservable {

    var moneySaved: ObservableFloat = ObservableFloat()
    var moneySpend: ObservableDouble = ObservableDouble()
    var actualMoney: ObservableDouble = ObservableDouble()


    constructor(moneySaved:Float, moneySpend: Double) {
        updateMoney(moneySaved,moneySpend)
    }

    fun updateMoney(moneySaved:Float, moneySpend: Double) {
        this.moneySaved.set(moneySaved)
        this.moneySpend.set(moneySpend)
        this.actualMoney.set(moneySaved - moneySpend)
    }
}