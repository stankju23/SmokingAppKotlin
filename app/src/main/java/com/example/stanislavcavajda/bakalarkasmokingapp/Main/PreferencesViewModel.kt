package com.example.stanislavcavajda.bakalarkasmokingapp.Main

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Context.ACTIVITY_SERVICE
import android.content.DialogInterface
import android.content.Intent
import android.databinding.BaseObservable
import android.databinding.ObservableField
import android.support.v7.app.AlertDialog
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Spinner
import android.widget.Toast
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager.Wish
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.JSONParser
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.NotificationScheduler
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.Walkthrough.Walkthrough

class PreferencesViewModel : BaseObservable {

    var cigarretesPerDay: ObservableField<String> = ObservableField()
    var cigarretesInPacket: ObservableField<String> = ObservableField()
    var packetCost: ObservableField<String> = ObservableField()
    var currency: ObservableField<String> = ObservableField()
    var context: Context

    constructor(cigarretesPerDay: String, cigarretesInPacket: String, packetCost: String, context: Context,currency: String) {
        updatePreferences(cigarretesPerDay, cigarretesInPacket, packetCost,currency)
        this.context = context
    }

    fun updatePreferences(cigarretesPerDay: String, cigarretesInPacket: String, packetCost: String,currency:String) {
        this.cigarretesPerDay.set(cigarretesPerDay)
        this.cigarretesInPacket.set(cigarretesInPacket)
        this.packetCost.set(packetCost)
        this.currency.set(currency)
    }

    fun setCigarettesPerDay(v: View) {

        var preferences = (context as Activity).getSharedPreferences("actualState", Context.MODE_PRIVATE)
        var editor = preferences.edit()

        var builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.cigarettes_smoked_every_day)
        builder.setMessage(R.string.set_value)

        var layout = FrameLayout(context)
        var setValueEditText = EditText(context)
        setValueEditText.inputType = InputType.TYPE_CLASS_NUMBER
        setValueEditText.setText(cigarretesPerDay.get())
        layout.addView(setValueEditText)
        layout.setPadding(45, 0, 45, 0)
        builder.setView(layout)

        builder.setPositiveButton(R.string.yes, DialogInterface.OnClickListener { dialogInterface, i ->
            var value = setValueEditText.text.toString()
            if (value.length != 0 || value.toInt() < 0) {
                cigarretesPerDay.set(value)
                Data.MoneyDashboard.cigarretesPerDay = value.toInt()
                editor.putInt(Constants.actualState.CIGARETTES_PER_DAY, value.toInt())
                editor.commit()
                updateWishesNotifcations(Data.wishList)
            } else {
                Toast.makeText(context, "Value isnt correct", Toast.LENGTH_SHORT).show()
            }
        })

        builder.setNegativeButton(R.string.cancel, DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.dismiss() })

        var dialog = builder.create()
        dialog.show()
    }

    fun setCigarettesInPacket(v: View) {

        var preferences = (context as Activity).getSharedPreferences("actualState", Context.MODE_PRIVATE)
        var editor = preferences.edit()

        var builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.cigarettes_in_packet)
        builder.setMessage(R.string.set_value)

        var layout = FrameLayout(context)
        var setValueEditText = EditText(context)
        setValueEditText.inputType = InputType.TYPE_CLASS_NUMBER
        setValueEditText.setText(cigarretesInPacket.get())
        layout.addView(setValueEditText)
        layout.setPadding(45, 0, 45, 0)
        builder.setView(layout)

        builder.setPositiveButton(R.string.yes, DialogInterface.OnClickListener { dialogInterface, i ->
            var value = setValueEditText.text.toString()
            if (value.length != 0 || value.toInt() < 0) {
                cigarretesInPacket.set(value)
                Data.MoneyDashboard.cigarretesInPackage = value.toInt()
                editor.putInt(Constants.actualState.CIGARETTES_IN_PACKAGE, value.toInt())
                editor.commit()
                updateWishesNotifcations(Data.wishList)
            } else {
                Toast.makeText(context, "Value isnt correct", Toast.LENGTH_SHORT).show()
            }
        })

        builder.setNegativeButton(R.string.cancel, DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.dismiss() })

        var dialog = builder.create()
        dialog.show()
    }

    fun setPacketCost(v: View) {

        var preferences = (context as Activity).getSharedPreferences("actualState", Context.MODE_PRIVATE)
        var editor = preferences.edit()

        var builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.packet_cost)
        builder.setMessage(R.string.set_value)

        var layout = FrameLayout(context)
        var setValueEditText = EditText(context)
        setValueEditText.inputType = InputType.TYPE_CLASS_NUMBER
        setValueEditText.setKeyListener(DigitsKeyListener.getInstance("0123456789."))
        setValueEditText.setText(packetCost.get())
        layout.addView(setValueEditText)
        layout.setPadding(45, 0, 45, 0)
        builder.setView(layout)

        builder.setPositiveButton(R.string.yes, DialogInterface.OnClickListener { dialogInterface, i ->
            var value = setValueEditText.text.toString()
            if (value.length != 0 || value.toInt() < 0) {
                packetCost.set(value)
                Data.MoneyDashboard.packagePrice = value.toDouble()
                editor.putFloat(Constants.actualState.PACKAGE_PRICE, value.toFloat())
                editor.commit()
                updateWishesNotifcations(Data.wishList)
            } else {
                Toast.makeText(context, "Value isnt correct", Toast.LENGTH_SHORT).show()
            }
        })

        builder.setNegativeButton(R.string.cancel, DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.dismiss() })

        var dialog = builder.create()
        dialog.show()
    }

    fun setCurrency(v: View) {

        var preferences = (context as Activity).getSharedPreferences("actualState", Context.MODE_PRIVATE)
        var editor = preferences.edit()

        var builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.packet_cost)
        builder.setMessage(R.string.set_value)

        var layout = FrameLayout(context)
        var currencySpinner = Spinner(context)

        var adapter = ArrayAdapter<String>(context,android.R.layout.simple_dropdown_item_1line, JSONParser.parseCurrencies(context))
        currencySpinner.adapter = adapter

        layout.addView(currencySpinner)
        layout.setPadding(45, 0, 45, 0)
        builder.setView(layout)

        builder.setPositiveButton(R.string.yes, DialogInterface.OnClickListener { dialogInterface, i ->
            var value = currencySpinner.selectedItem.toString()
                currency.set(value)
                Data.MoneyDashboard.currency = value
                editor.putString(Constants.actualState.CURRENCY, value.toString())
                editor.commit()
                updateWishesNotifcations(Data.wishList)
        })

        builder.setNegativeButton(R.string.cancel, DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.dismiss() })

        var dialog = builder.create()
        dialog.show()
    }

    fun factoryReset(v: View) {
        var builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.factory_reset)
        builder.setMessage(R.string.factory_reset_desc)

        builder.setPositiveButton(R.string.yes, DialogInterface.OnClickListener { dialogInterface, i ->
            //            RealmDB.realm.beginTransaction()
//            RealmDB.realm.deleteAll()
//            RealmDB.realm.commitTransaction()
//            DataManager.deleteMainData(context)
            (context.getSystemService(ACTIVITY_SERVICE) as ActivityManager).clearApplicationUserData()
            var walkthrough = Intent(context, Walkthrough::class.java)
            context.startActivity(walkthrough)
        })

        builder.setNegativeButton(R.string.cancel, DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.dismiss() })

        var dialog = builder.create()
        dialog.show()
    }

    fun updateWishesNotifcations(list: ArrayList<Wish>) {
        var nS = NotificationScheduler()
        for (i in 0..list.size - 1) {
            var wish = list[i]
            if (!wish.isBought.get() && !wish.canBuy.get()) {
                nS.scheduleNotification(wish.title.get()!!, wish.desc.get()!!, R.drawable.currency_image, context, 21 + i, wish.endTime, true, false)
            }
        }
    }
}