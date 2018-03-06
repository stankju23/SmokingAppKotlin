package com.example.stanislavcavajda.bakalarkasmokingapp.Helper

import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.HealthList.HealthProgressViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager.Wish

/**
 * Created by stanislavcavajda on 07/02/2018.
 */
object Data {

    var healthProgressViewList: ArrayList<HealthProgressViewModel> = ArrayList()
    var date = "02-03-2018 15:02"
    var healthProgressTimes: ArrayList<Long> = ArrayList()
    var wishList: ArrayList<Wish> = ArrayList()

    object MoneyDashboard {
        var moneySaved:Float = 0.0f
        var moneySpend:Double = 0.0
        var cigarretesPerDay: Int = 15
        var cigarretesInPackage:Int = 20
        var packagePrice:Double = 3.40
    }
}