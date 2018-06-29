package com.example.stanislavcavajda.bakalarkasmokingapp.Walkthrough

import android.content.Intent
import android.databinding.ObservableField
import android.graphics.drawable.Drawable
import android.view.View
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Main.MainActivity

class WalkhroughItem {

    var title: String
    var image: Drawable
    var text: String
    var value:ObservableField<String> = ObservableField()


    constructor(title: String, image: Drawable, text:String, value:String) {
        this.title = title
        this.image = image
        this.text = text
        this.value.set(value)
    }

    fun add1 (view: View) {
        var newValue = this.value.get()?.toInt()
        if (newValue != null && newValue < 30) {
            newValue++
            this.value.set(newValue.toString())
        }
    }

    fun sub1 (view:View) {
        var newValue = this.value.get()?.toInt()
        if (newValue != null && newValue > 5) {
            newValue--
            this.value.set(newValue.toString())
        }
    }

    fun startNewActivity(view: View) {

        Data.MoneyDashboard.cigarretesPerDay = (Data.walkthroughList[1] as WalkhroughItem).value.get()?.toInt()!!
        Data.MoneyDashboard.cigarretesInPackage = (Data.walkthroughList[2] as WalkhroughItem).value.get()?.toInt()!!
        //Data.MoneyDashboard.packagePrice = (Data.walkthroughList[3] as WalkhroughItem).value.get()?.toInt()!!

        var intent = Intent(view.context,MainActivity::class.java)
        view.context.startActivity(intent)
    }
}
