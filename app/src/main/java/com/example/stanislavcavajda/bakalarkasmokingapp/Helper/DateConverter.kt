package com.example.stanislavcavajda.bakalarkasmokingapp.Helper

import android.annotation.SuppressLint
import android.content.Context
import android.icu.util.Calendar
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.MainProgress.MainProgressDetailTime
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

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


    @SuppressLint("NewApi")
    fun getDate(time: Long): String {
        val calendar = Calendar.getInstance()
        val tz = TimeZone.getDefault()
        calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.timeInMillis))
        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        val currenTimeZone = java.util.Date(time * 1000)
        return sdf.format(currenTimeZone)
    }



    fun updateMainProgressDetail(context: Context, timestamp: Long) : ArrayList<MainProgressDetailTime> {
        var timeList = ArrayList<MainProgressDetailTime>()

        if (timestamp/60/60/24 > 0) {
            timeList.add(MainProgressDetailTime((timestamp/60/60/24).toInt(), context.resources.getString(R.string.days)))
        }

        if (timestamp/60/60 > 0) {
            timeList.add(MainProgressDetailTime((timestamp/60/60).toInt(), context.resources.getString(R.string.hours)))
        }

        if (timestamp/60 > 0) {
            timeList.add(MainProgressDetailTime((timestamp/60).toInt(), context.resources.getString(R.string.minutes)))
        }
        timeList.add(MainProgressDetailTime(timestamp.toInt(), context.resources.getString(R.string.seconds)))

        return timeList
    }
}