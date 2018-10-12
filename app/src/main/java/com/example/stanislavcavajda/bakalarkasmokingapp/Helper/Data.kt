package com.example.stanislavcavajda.bakalarkasmokingapp.Helper

import com.example.stanislavcavajda.bakalarkasmokingapp.Cravings.CravingItem
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.HealthList.HealthProgressViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.MainProgress.MainProgressDetailTime
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager.Wish
import com.example.stanislavcavajda.bakalarkasmokingapp.Journal.Journal
import com.example.stanislavcavajda.bakalarkasmokingapp.Koloda.KolodaItem
import com.example.stanislavcavajda.bakalarkasmokingapp.Main.Achievment
import com.example.stanislavcavajda.bakalarkasmokingapp.Missions.Mission
import com.example.stanislavcavajda.bakalarkasmokingapp.Missions.Objective

/**
 * Created by stanislavcavajda on 07/02/2018.
 */
object Data {


    var firstTime:Boolean = true

    var healthProgressViewList: ArrayList<HealthProgressViewModel> = ArrayList()
    //var date = "02-03-2018 15:00"
    var date = "18-08-2018 11:13:00"
    var healthProgressTimes: ArrayList<Long> = ArrayList()
    var wishList: ArrayList<Wish> = ArrayList()
    var timeList: ArrayList<MainProgressDetailTime> = ArrayList()
    var achievmentList: ArrayList<Achievment> = ArrayList()
    var missionsAchievments: ArrayList<Achievment> = ArrayList()
    var cravingsCardList: ArrayList<KolodaItem> = ArrayList()
    var journalCardSList: ArrayList<Journal> = ArrayList()
    var numberOfObjectivesInCravings:Int = 3

    var objectives: ArrayList<Objective> = ArrayList()
    var missionList: ArrayList<Mission> = ArrayList()
    var actualTheme = 0
    var cravings:ArrayList<CravingItem> = ArrayList()

    var dashboardThreadStared = false
    var themeChanged = false

    var actualClickedMission = 0
    var actualClickedActivity = 0


    var notificationChannel: String = "My Notification Channel"



    object Theme {
        var mainSelectedColor = "#558d96"
    }


    object MoneyDashboard {
        var moneySaved:Float =0.0f
        var moneySpend:Float = 0.0f
        var cigarretesPerDay: Int = 15
        var cigarretesInPackage:Int = 20
        var currency:String = ""
        var packagePrice:Double = 3.3
        var actualMoneyState:Float = 0.0f
    }
}