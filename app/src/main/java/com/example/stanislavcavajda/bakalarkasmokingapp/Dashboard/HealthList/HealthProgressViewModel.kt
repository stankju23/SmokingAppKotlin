package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.HealthList

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.ObservableField
import android.view.View
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.Model.Date

/**
 * Created by stanislavcavajda on 27/02/2018.
 */
class HealthProgressViewModel {

    var id: Int = 0
    var title: String
    var date: ObservableField<Date> = ObservableField()
    var desc: String
    var context: Context

    constructor(id:Int,title: String,date: Date, desc:String, context: Context) {
        this.id = id
        this.title = title
        this.date.set(date)
        this.desc = desc
        this.context = context
    }

    fun showDetail(v: View) {
        var healthProgressDetail = Intent((context as Activity),HealthProgressDetailActivity::class.java)
        healthProgressDetail.putExtra(Constants.extras.EXTRA_ITEM_ID, this.id)
        (context as Activity).startActivity(healthProgressDetail)
    }
}