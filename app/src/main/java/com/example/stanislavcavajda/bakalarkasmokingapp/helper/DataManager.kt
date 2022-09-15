package com.example.stanislavcavajda.bakalarkasmokingapp.helper

import android.content.Context
import java.text.DecimalFormat

object DataManager{

    fun loadMainData(context: Context) {
        var preferences = context.getSharedPreferences("actualState", Context.MODE_PRIVATE)
        Data.MoneyDashboard.cigarretesPerDay = preferences.getInt(Constants.actualState.CIGARETTES_PER_DAY, 0)
        Data.MoneyDashboard.cigarretesInPackage = preferences.getInt(Constants.actualState.CIGARETTES_IN_PACKAGE, 0)
        var decimalFormat = DecimalFormat("###.##")
        var result = decimalFormat.format(preferences.getFloat(Constants.actualState.PACKAGE_PRICE,0f).toDouble())

        result = result.replace(",",".")

        Data.MoneyDashboard.packagePrice = result.toDouble()
        Data.MoneyDashboard.currency = preferences.getString(Constants.actualState.CURRENCY, "").toString()
        Data.actualTheme = preferences.getInt(Constants.actualState.THEME,0)
        Data.date = preferences.getString(Constants.actualState.ACTUAL_DATE,"").toString()
    }

    fun deleteMainData(context: Context) {
        var preferences = context.getSharedPreferences("actualState", Context.MODE_PRIVATE)
        var editor = preferences.edit()
        editor.putInt(Constants.actualState.CIGARETTES_PER_DAY, 0)
        editor.putInt(Constants.actualState.CIGARETTES_IN_PACKAGE, 0)
        editor.putFloat(Constants.actualState.PACKAGE_PRICE,0f)
        editor.putString(Constants.actualState.CURRENCY, "")
        editor.putInt(Constants.actualState.THEME,0)
        editor.putString(Constants.actualState.ACTUAL_DATE,"")
        editor.commit()

        var walkthroughPref = context.getSharedPreferences("walkthrough", Context.MODE_PRIVATE)
        var walkthrougEdit = walkthroughPref.edit()
        walkthrougEdit.putBoolean(Constants.preferences.FIRST_TIME,false)
        walkthrougEdit.commit()
    }
}