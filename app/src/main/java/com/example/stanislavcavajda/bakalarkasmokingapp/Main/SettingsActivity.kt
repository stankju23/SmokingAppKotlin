package com.example.stanislavcavajda.bakalarkasmokingapp.Main

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.DatePicker
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.DateConverter
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.github.bluzwong.swipeback.SwipeBackActivityHelper
import kotlinx.android.synthetic.main.activity_settings.*


class SettingsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        var helper = SwipeBackActivityHelper()
        helper.setDebuggable(true)
                .setEdgeMode(false)
                .setParallaxMode(false)
                .setParallaxRatio(3)
                .setNeedBackgroundShadow(false)
                .init(this)

        var date = DateConverter()
        var convertedDate = date.convertToDatePicker(Data.date)


        datePicker.init(convertedDate[2],convertedDate[1] - 1,convertedDate[0], DatePicker.OnDateChangedListener {
            datePicker, year, month, day ->

            var den = ""
            if (day < 10) {
                den = "0$day"
            } else {
                den = "$day"
            }

            var mesiac = ""
            if (month < 10) {
                mesiac = "0${month + 1}"
            } else {
                mesiac = "$month"
            }

            Data.date = "$den-$mesiac-$year"

            var preference = getSharedPreferences("date", Context.MODE_PRIVATE)
            var editor = preference.edit()
            editor.putString(Constants.preferences.DATE_PREFERENCES,Data.date)
            editor.commit()

        })

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
