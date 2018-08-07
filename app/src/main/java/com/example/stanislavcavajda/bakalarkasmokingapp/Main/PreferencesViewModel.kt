package com.example.stanislavcavajda.bakalarkasmokingapp.Main

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.databinding.BaseObservable
import android.databinding.ObservableField
import android.support.v7.app.AlertDialog
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data

class PreferencesViewModel : BaseObservable {

    var cigarretesPerDay: ObservableField<String> = ObservableField()
    var cigarretesInPacket: ObservableField<String> = ObservableField()
    var packetCost: ObservableField<String> = ObservableField()
    var context: Context


    constructor(cigarretesPerDay:String,cigarretesInPacket:String,packetCost:String,context: Context) {
        updatePreferences(cigarretesPerDay,cigarretesInPacket,packetCost)
        this.context = context
    }

    fun updatePreferences(cigarretesPerDay:String,cigarretesInPacket:String,packetCost:String) {
        this.cigarretesPerDay.set(cigarretesPerDay)
        this.cigarretesInPacket.set(cigarretesInPacket)
        this.packetCost.set(packetCost)
    }

    fun setCigarettesPerDay(v:View) {

        var preferences = (context as Activity).getSharedPreferences("actualState",Context.MODE_PRIVATE)
        var editor = preferences.edit()

        var builder = AlertDialog.Builder(context)
        builder.setTitle("Cigarettes smoked per day")
        builder.setMessage("Set value")

        var layout = FrameLayout(context)
        var setValueEditText = EditText(context)
        setValueEditText.inputType = InputType.TYPE_CLASS_NUMBER
        setValueEditText.setText(cigarretesPerDay.get())
        layout.addView(setValueEditText)
        layout.setPadding(45,0,45,0)
        builder.setView(layout)

        builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
            var value = setValueEditText.text.toString()
            if (value.length != 0 || value.toInt() < 0) {
                cigarretesPerDay.set(value)
                Data.MoneyDashboard.cigarretesPerDay = value.toInt()
                editor.putInt(Constants.actualState.CIGARETTES_PER_DAY, value.toInt())
                editor.commit()
            } else {
                Toast.makeText(context,"Value isnt correct",Toast.LENGTH_SHORT).show()
            }
        })

        builder.setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.dismiss() })


        var dialog = builder.create()
        dialog.show()
    }

    fun setCigarettesInPacket(v:View) {

        var preferences = (context as Activity).getSharedPreferences("actualState",Context.MODE_PRIVATE)
        var editor = preferences.edit()

        var builder = AlertDialog.Builder(context)
        builder.setTitle("Cigarettes in packet")
        builder.setMessage("Set value")

        var layout = FrameLayout(context)
        var setValueEditText = EditText(context)
        setValueEditText.inputType = InputType.TYPE_CLASS_NUMBER
        setValueEditText.setText(cigarretesInPacket.get())
        layout.addView(setValueEditText)
        layout.setPadding(45,0,45,0)
        builder.setView(layout)

        builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
            var value = setValueEditText.text.toString()
            if (value.length != 0 || value.toInt() < 0) {
                cigarretesInPacket.set(value)
                Data.MoneyDashboard.cigarretesInPackage = value.toInt()
                editor.putInt(Constants.actualState.CIGARETTES_IN_PACKAGE, value.toInt())
                editor.commit()
            } else {
                Toast.makeText(context,"Value isnt correct",Toast.LENGTH_SHORT).show()
            }
        })

        builder.setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.dismiss() })


        var dialog = builder.create()
        dialog.show()
    }

    fun setPacketCost(v:View) {

        var preferences = (context as Activity).getSharedPreferences("actualState",Context.MODE_PRIVATE)
        var editor = preferences.edit()

        var builder = AlertDialog.Builder(context)
        builder.setTitle("Packet cost")
        builder.setMessage("Set value")

        var layout = FrameLayout(context)
        var setValueEditText = EditText(context)
        setValueEditText.inputType = InputType.TYPE_CLASS_NUMBER
        setValueEditText.setKeyListener(DigitsKeyListener.getInstance("0123456789."))
        setValueEditText.setText(packetCost.get())
        layout.addView(setValueEditText)
        layout.setPadding(45,0,45,0)
        builder.setView(layout)

        builder.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
            var value = setValueEditText.text.toString()
            if (value.length != 0 || value.toInt() < 0) {
                packetCost.set(value)
                Data.MoneyDashboard.packagePrice = value.toDouble()
                editor.putFloat(Constants.actualState.PACKAGE_PRICE, value.toFloat())
                editor.commit()
            } else {
                Toast.makeText(context,"Value isnt correct",Toast.LENGTH_SHORT).show()
            }
        })

        builder.setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.dismiss() })


        var dialog = builder.create()
        dialog.show()
    }
}