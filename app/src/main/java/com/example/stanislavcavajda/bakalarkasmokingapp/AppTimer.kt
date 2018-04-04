package com.example.stanislavcavajda.bakalarkasmokingapp

import java.util.Timer
import java.util.TimerTask

/**
 * Created by stanislavcavajda on 04/04/2018.
 */
object AppTimer {

    var timer = Timer().scheduleAtFixedRate(object :TimerTask() {
        override fun run() {

        }
    },0,100)
}