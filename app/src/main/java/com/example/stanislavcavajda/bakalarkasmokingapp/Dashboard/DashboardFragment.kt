package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard


import android.app.Fragment
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.HealthList.HealthProgressListViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.MainProgress.MainProgressViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.MoneySaved.MoneySavedViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager.Wish
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager.WishListViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.DateConverter
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.JSONParser
import com.example.stanislavcavajda.bakalarkasmokingapp.Model.Date
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.FragmentDashboardBinding
import java.text.NumberFormat
import java.util.Locale
import java.util.Timer
import java.util.TimerTask

class DashboardFragment : Fragment() {


    var dateConverter = DateConverter()
    var progress = 0f
    lateinit var dashboardList: DashboardListViewModel
    lateinit var date: Date
    var currentTimestamp: Long = 0L
    var dateTimestamp: Long = 0L
    lateinit var recycler: RecyclerView

    var timer: Timer = Timer()
    var dashboardTimer = Timer()
    var wordList = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var binding:FragmentDashboardBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)

        var view = binding.root

        recycler = view.findViewById<RecyclerView>(R.id.dashboard_recycler_view)
        recycler.setItemViewCacheSize(20);
        recycler.setDrawingCacheEnabled(true);
        recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        date = Date(dateConverter.getCurrentTimestamp() - dateConverter.convertDateToTimestamp(Data.date),Constants.timeConst.twentyOneDays)

        if(Data.healthProgressViewList.isEmpty()) {
            prepareHealthProgressList()
        }


        dashboardList = DashboardListViewModel(ArrayList(), activity)

        var healthProgress = HealthProgressListViewModel(Data.healthProgressViewList, activity)
        var mainProgress = MainProgressViewModel(date, activity,Data.timeList)
        var wishesManager = WishListViewModel(Data.wishList,activity)
        var moneySaved = MoneySavedViewModel(Data.MoneyDashboard.moneySaved,Data.MoneyDashboard.moneySpend)


        dashboardList.list.add(mainProgress as Object)
        dashboardList.list.add(healthProgress as Object)
        dashboardList.list.add(wishesManager as Object)
        dashboardList.list.add(moneySaved as Object)

        wordList = ArrayList<String>()
        wordList.add(resources.getString(R.string.days))
        wordList.add(resources.getString(R.string.hours))
        wordList.add(resources.getString(R.string.minutes))
        wordList.add(resources.getString(R.string.seconds))

        var result = ""
        val nf = NumberFormat.getInstance(Locale.US)

        Log.i("Dashboard fragment","created")

        currentTimestamp = dateConverter.getCurrentTimestamp()
        dateTimestamp = dateConverter.convertDateToTimestamp(Data.date)
        //result = "%.2f".format(actualSaved(currentTimestamp - dateTimestamp,Data.MoneyDashboard.cigarretesPerDay,Data.MoneyDashboard.packagePrice, Data.MoneyDashboard.cigarretesInPackage))

        Data.MoneyDashboard.actualMoneyState = Data.MoneyDashboard.moneySaved - Data.MoneyDashboard.moneySpend
        updateWishList()


        dashboardTimer.scheduleAtFixedRate(object : TimerTask(){
            override fun run() {
                if (activity != null) {
                    currentTimestamp = dateConverter.getCurrentTimestamp()
                    dateTimestamp = dateConverter.convertDateToTimestamp(Data.date)
                    date = Date(currentTimestamp - dateTimestamp, Constants.timeConst.twentyOneDays)

                    progress = (dateConverter.getCurrentTimestamp() - dateConverter.convertDateToTimestamp(Data.date)).toFloat() / (Constants.timeConst.twentyOneDays).toFloat() * 100f

                    (dashboardList.list.get(Constants.viewTypes.MAIN_PROGRESS_VIEW_TYPE) as MainProgressViewModel).setProgress(date)

                    Data.timeList.clear()
                    Data.timeList.addAll(dateConverter.updateMainProgressDetail(currentTimestamp-dateTimestamp,wordList))

                    (dashboardList.list.get(Constants.viewTypes.MAIN_PROGRESS_VIEW_TYPE) as MainProgressViewModel).updateDetail(Data.timeList)

                    updateHealthProgress(currentTimestamp, dateTimestamp)


                    (dashboardList.list.get(Constants.viewTypes.HEALTH_PROGRESS_VIEW_TYPE) as HealthProgressListViewModel).updateAll()
                    //result = "%.2f".format(actualSaved(currentTimestamp - dateTimestamp, Data.MoneyDashboard.cigarretesPerDay, Data.MoneyDashboard.packagePrice, Data.MoneyDashboard.cigarretesInPackage))


                }
            }
        },1000,1000)


//

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                try {
                    var result = actualSaved(currentTimestamp - dateTimestamp,Data.MoneyDashboard.cigarretesPerDay,Data.MoneyDashboard.packagePrice, Data.MoneyDashboard.cigarretesInPackage).toString()

                    //Data.MoneyDashboard.moneySaved = nf.parse(result).toFloat() / 100
                    Data.MoneyDashboard.moneySaved = (result).toFloat()

                    (dashboardList.list.get(Constants.viewTypes.MONEY_SAVED_VIEW_TYPE) as MoneySavedViewModel).updateMoney(
                            Data.MoneyDashboard.moneySaved, 0.0f)

                    updateWishList()

                    val list = Data.wishList.sortedWith(compareBy(Wish::price))
                    Data.wishList = ArrayList(list)

                    (dashboardList.list.get(Constants.viewTypes.WISHES_MANAGER_VIEW_TYPE) as WishListViewModel).updateWishList(Data.wishList)

                    Data.MoneyDashboard.actualMoneyState = Data.MoneyDashboard.moneySaved - Data.MoneyDashboard.moneySpend

                }catch (e:Exception) {

                }

            }
        },1000,1)



        Data.dashboardThreadStared = true



        binding.viewModel = dashboardList



        return view
    }

    fun actualSaved(actualTimestamp:Long, cigarretesPerDay: Int, packagePrice:Double,inPackage:Int):Float {
        var result = 0.0f
        var day = 86400
        result = (((packagePrice.toFloat()/ inPackage.toFloat()) * cigarretesPerDay)/ day.toFloat()) * actualTimestamp
        return result
    }

    fun updateWishList() {
        for (i in Data.wishList) {
            i.setWish(i.title.get()!!,i.desc.get()!!,i.price,i.image!!)
        }
    }


    fun prepareHealthProgressList() {
        Data.healthProgressViewList.addAll(JSONParser.parseHealthData(JSONParser.loadJsonFromAssets("HealthData",activity),activity))
    }


    fun updateHealthProgress(currentTimestamp: Long, dateTimestamp: Long) {
        var index = 0
        for (item in Data.healthProgressTimes) {
            date = Date(dateTimestamp + item - currentTimestamp,item)
            Data.healthProgressViewList[index].date.set(date)
            index++
        }
    }

    override fun onDestroy() {
        Log.i("fragment","destroyed")
        timer.cancel()
        dashboardTimer.cancel()
        super.onDestroy()
    }

    override fun onStop() {
        super.onStop()

        Log.i("Dashboard fragment","stopped")

    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        Log.i("Thread", "resumed")
        super.onResume()
    }


}
