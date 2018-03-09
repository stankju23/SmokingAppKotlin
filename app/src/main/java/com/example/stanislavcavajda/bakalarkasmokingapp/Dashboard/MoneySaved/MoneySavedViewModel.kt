package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.MoneySaved

import android.databinding.BaseObservable
import android.databinding.ObservableFloat

/**
 * Created by stanislavcavajda on 06/03/2018.
 */
class MoneySavedViewModel: BaseObservable {

    var moneySaved: ObservableFloat = ObservableFloat()
    var moneySpend: ObservableFloat = ObservableFloat()


    constructor(moneySaved:Float, moneySpend: Float) {
        updateMoney(moneySaved,moneySpend)
    }

    fun updateMoney(moneySaved:Float, moneySpend: Float) {
        this.moneySaved.set(moneySaved)
        this.moneySpend.set(moneySpend)
    }
}