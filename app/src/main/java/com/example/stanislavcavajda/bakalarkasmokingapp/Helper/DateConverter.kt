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
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
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
}