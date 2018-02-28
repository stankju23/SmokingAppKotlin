package com.example.stanislavcavajda.bakalarkasmokingapp.Helper

import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.HealthList.HealthProgressViewModel

/**
 * Created by stanislavcavajda on 07/02/2018.
 */
object Data {

    var healthProgressViewList: ArrayList<HealthProgressViewModel> = ArrayList()
    var date = ""

    var healthProgressTimes: ArrayList<Long> = arrayListOf(
        Constants.timeConst.twentyMinutes,
        Constants.timeConst.oneHour,
        Constants.timeConst.twoHours,
        Constants.timeConst.twelveHours,
        Constants.timeConst.oneDay,
        Constants.timeConst.twoDays,
        Constants.timeConst.seventyTwoHours,
        Constants.timeConst.eightDays,
        Constants.timeConst.twoWeeks,
        Constants.timeConst.twentyOneDays,
        Constants.timeConst.fourWeeks,
        Constants.timeConst.eightWeeks,
        Constants.timeConst.treeMonths,
        Constants.timeConst.nineMonths,
        Constants.timeConst.oneYear,
        Constants.timeConst.fiveYears,
        Constants.timeConst.tenYears,
        Constants.timeConst.fifteenYears,
        Constants.timeConst.thirteenYears,
        Constants.timeConst.twentyYears
    )


}