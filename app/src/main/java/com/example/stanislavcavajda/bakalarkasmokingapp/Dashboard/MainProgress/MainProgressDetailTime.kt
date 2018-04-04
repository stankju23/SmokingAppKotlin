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
        var formatNumber = this.number.toString()
        var newNumber = formatNumber
        if (this.number > 1000000) {
            newNumber = ""
            newNumber += formatNumber.substring(0,formatNumber.length - 6)
            newNumber += " "
            newNumber += formatNumber.substring(formatNumber.length - 6,formatNumber.length - 3)
            newNumber += " "
            newNumber += formatNumber.substring(formatNumber.length - 3,formatNumber.length)
        } else if (this.number < 1000000 && this.number > 1000){
            newNumber = ""
            newNumber += formatNumber.substring(0,formatNumber.length - 3)
            newNumber += " "
            newNumber += formatNumber.substring(formatNumber.length - 3,formatNumber.length)
        }
        return "$newNumber $numberInfo"
    }
}