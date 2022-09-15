package com.example.stanislavcavajda.bakalarkasmokingapp.dashboard.moneysaved

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import androidx.databinding.ObservableFloat

/**
 * Created by stanislavcavajda on 06/03/2018.
 */
class MoneySavedViewModel: BaseObservable {

    var moneySaved: ObservableFloat = ObservableFloat()
    var moneySpend: ObservableFloat = ObservableFloat()
    var currency:ObservableField<String> = ObservableField()


    constructor(moneySaved:Float, moneySpend: Float,currency:String) {
        updateMoney(moneySaved,moneySpend,currency)
    }

    fun updateMoney(moneySaved:Float, moneySpend: Float,currency:String) {
        this.moneySaved.set(moneySaved)
        this.moneySpend.set(moneySpend)
        this.currency.set(currency)
    }
}