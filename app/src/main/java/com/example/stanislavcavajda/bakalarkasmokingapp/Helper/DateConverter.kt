package com.example.stanislavcavajda.bakalarkasmokingapp.Helper

import java.sql.Timestamp
import java.text.SimpleDateFormat



public class DateConverter () {


    // getting current timestamp
    fun getCurrentTimestamp() : Long {
        val time = System.currentTimeMillis()
        val timestamp = Timestamp(time)
        return timestamp.time / 1000
    }

    // converting date to timestamp
    fun convertDateToTimestamp(date: String) : Long {
        val timestamp: Timestamp
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy kk:mm")
        val date = simpleDateFormat.parse(date)
        timestamp = Timestamp(date.time)
        return timestamp.time / 1000
    }

    fun convertToDatePicker(date: String):ArrayList<Int> {
        var dateList = ArrayList<Int>()
        dateList.add(date.substring(0,2).toInt())
        dateList.add(date.substring(3,5).toInt())
        dateList.add(date.substring(6,10).toInt())
        return dateList
    }

//    fun updateMainProgressDetail(context: Context,timestamp: Long) : ArrayList<TimeFormat> {
//        var timeList = ArrayList<TimeFormat>()
//
//        if (timestamp/60/60/24 > 0) {
//            timeList.add(TimeFormat((timestamp/60/60/24).toInt(), context.resources.getString(R.string.days)))
//        }
//
//        if (timestamp/60/60 > 0) {
//            timeList.add(TimeFormat((timestamp/60/60).toInt(), context.resources.getString(R.string.hours)))
//        }
//
//        if (timestamp/60 > 0) {
//            timeList.add(TimeFormat((timestamp/60).toInt(), context.resources.getString(R.string.minutes)))
//        }
//        timeList.add(TimeFormat(timestamp.toInt(), context.resources.getString(R.string.seconds)))
//
//        return timeList
//    }
}