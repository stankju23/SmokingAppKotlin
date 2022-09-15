package com.example.stanislavcavajda.bakalarkasmokingapp.model

/**
 * Created by stanislavcavajda on 07/02/2018.
 */

class Date {

    var days = 0L
    var hours = 0L
    var minutes = 0L
    var seconds = 0L
    var progress = 0f

    constructor(currentTimestamp: Long,finishTimestamp: Long){
        setDate(currentTimestamp,finishTimestamp)
    }

    fun setDate (currentTimestamp: Long, finishTimestamp: Long) {
        if(currentTimestamp >= 0 ) {
            this.days = currentTimestamp / 60 / 60 / 24
            this.hours = (currentTimestamp - (days * 24 * 60 * 60)) / 60 / 60
            this.minutes = (currentTimestamp - (days * 24 * 60 * 60) - (hours * 60 * 60)) / 60
            this.seconds = (currentTimestamp - (days * 24 * 60 * 60) - (hours * 60 * 60) - (minutes * 60))
            this.progress = 100 - (currentTimestamp.toFloat() / finishTimestamp.toFloat() * 100f)
        } else {
            this.days = 0
            this.hours = 0
            this.minutes = 0
            this.seconds = 0
            this.progress = 100f
        }

    }
}