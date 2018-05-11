package com.example.stanislavcavajda.bakalarkasmokingapp.Helper

import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.HealthList.HealthProgressViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.MainProgress.MainProgressDetailTime
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager.Wish
import com.example.stanislavcavajda.bakalarkasmokingapp.Missions.Mission
import com.example.stanislavcavajda.bakalarkasmokingapp.Missions.Objective

/**
 * Created by stanislavcavajda on 07/02/2018.
 */
object Data {

    var healthProgressViewList: ArrayList<HealthProgressViewModel> = ArrayList()
    //var date = "02-03-2018 15:00"
    var date = "03-05-2018 09:53"
    var healthProgressTimes: ArrayList<Long> = ArrayList()
    var wishList: ArrayList<Wish> = ArrayList()
    var timeList: ArrayList<MainProgressDetailTime> = ArrayList()
    var swipeCanClick: Boolean = true
    var objectives: ArrayList<Objective> = ArrayList()
    var missionList: ArrayList<Mission> = ArrayList()
    var actualTheme = 1

    var dashboardThreadStared = false
    var themeChanged = false



    object Theme {
        var mainSelectedColor = "#558d96"
    }


    object MoneyDashboard {
        var moneySaved:Float = 0.0f
        var moneySpend:Float = 0.0f
        var cigarretesPerDay: Int = 15
        var cigarretesInPackage:Int = 20
        var packagePrice:Double = 3.40
        var actualMoneyState:Float = 0.0f
    }
}