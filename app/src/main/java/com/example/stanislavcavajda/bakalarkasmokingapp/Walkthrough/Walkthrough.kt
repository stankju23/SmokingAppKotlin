package com.example.stanislavcavajda.bakalarkasmokingapp.Walkthrough

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityWalkthroughBinding

class Walkthrough : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this,Data.actualTheme)

        var binding:ActivityWalkthroughBinding = DataBindingUtil.setContentView(this,R.layout.activity_walkthrough)

        Data.walkthroughList.add(WalkhroughItem("Welcome, dear user",resources.getDrawable(R.drawable.welcome_image),"daskdasjkdaks dkas","dakjbdaksjdas") as Object)
        Data.walkthroughList.add(WalkhroughItem("Welcome, dear user",resources.getDrawable(R.drawable.cigarettes_per_day_image),"How many cigarettes did you smoke every day ?","20") as Object)
        Data.walkthroughList.add(WalkhroughItem("Welcome, dear user",resources.getDrawable(R.drawable.cigarettes_in_packet_image),"How many cigarettes are in one packet ?","20") as Object)
        Data.walkthroughList.add(WalkhroughItem("Welcome, dear user",resources.getDrawable(R.drawable.packet_cost_image),"How much cost one packet ?","3,30") as Object)
        Data.walkthroughList.add(WalkhroughItem("Welcome, dear user",resources.getDrawable(R.drawable.currency_image),"What is your prefered currency ?","€") as Object)
        var list:WalkthroughListViewModel = WalkthroughListViewModel(Data.walkthroughList,this)

        binding.viewModel = list

//        var button = findViewById<Button>(R.id.button7)
//
//        button.setOnClickListener {
//
//            Data.MoneyDashboard.cigarretesPerDay = (itemList[1] as WalkhroughItem).value.get()?.toInt()!!
//            Data.MoneyDashboard.cigarretesInPackage = (itemList[2] as WalkhroughItem).value.get()?.toInt()!!
//            Data.MoneyDashboard.packagePrice = (itemList[3] as WalkhroughItem).value.get()?.toDouble()!!
//            var intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
    }
}
