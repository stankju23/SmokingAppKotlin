package com.example.stanislavcavajda.bakalarkasmokingapp.helper

import android.app.Activity
import android.content.Context
import com.example.stanislavcavajda.bakalarkasmokingapp.R

/**
 * Created by stanislavcavajda on 15/04/2018.
 */
object ThemeManager {

    fun setTheme(context: Context,actualTheme:Int) {
        when (actualTheme) {
            Constants.Themes.pastel -> {
                context.setTheme(R.style.AppTheme)
            }
            Constants.Themes.blueOcean -> {
                context.setTheme(R.style.blueOcean)
            }
            Constants.Themes.wine -> {
                context.setTheme(R.style.wine)
            }
            Constants.Themes.banana -> {
                context.setTheme(R.style.banana)
            }

        }
    }

    fun recreateActivity(context: Context, colorChanged:Boolean,actualTheme:Int):Boolean {
        if (colorChanged) {
            when (actualTheme) {
                Constants.Themes.pastel -> {
                    (context as Activity).recreate()
                    return false
                }
                Constants.Themes.blueOcean -> {
                    (context as Activity).recreate()
                    return false
                }
                Constants.Themes.wine -> {
                    (context as Activity).recreate()
                    return false
                }
                Constants.Themes.banana -> {
                    (context as Activity).recreate()
                    return false
                }
            }
        }
        return false
    }
}