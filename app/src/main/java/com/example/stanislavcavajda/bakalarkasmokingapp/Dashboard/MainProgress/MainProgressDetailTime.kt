package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.MainProgress

/**
 * Created by stanislavcavajda on 09/03/2018.
 */
class MainProgressDetailTime {

    var number: Int
    var numberInfo:String

    constructor(number:Int,numberInfo:String) {
        this.number = number
        this.numberInfo = numberInfo
    }

    fun fullText() :String {
        return "$number $numberInfo"
    }
}