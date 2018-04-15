package com.example.stanislavcavajda.bakalarkasmokingapp.Helper

import android.app.Activity
import android.content.Context
import com.example.stanislavcavajda.bakalarkasmokingapp.R

/**
 * Created by stanislavcavajda on 15/04/2018.
 */
object ThemeManager {

    fun setTheme(context: Context,actualTheme:Int) {
        when (actualTheme) {
            Constants.Themes.theme1 -> {
                context.setTheme(R.style.AppTheme)
            }
            Constants.Themes.theme2 -> {
                context.setTheme(R.style.Theme1)
            }

        }
    }

    fun recreateActivity(context: Context, colorChanged:Boolean,actualTheme:Int):Boolean {
        if (colorChanged) {
            when (actualTheme) {
                Constants.Themes.theme1 -> {
                    (context as Activity).recreate()
                    return false
                }
                Constants.Themes.theme2 -> {
                    (context as Activity).recreate()
                    return false
                }

            }
        }
        return false
    }
}