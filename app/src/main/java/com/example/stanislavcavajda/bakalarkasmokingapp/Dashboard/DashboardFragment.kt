package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard


import android.app.Fragment
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.HealthList.HealthProgressListViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.HealthList.HealthProgressViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.DateConverter
import com.example.stanislavcavajda.bakalarkasmokingapp.Model.Date
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.FragmentDashboardBinding
import java.util.Timer
import kotlin.concurrent.timerTask

class DashboardFragment : Fragment() {


    var dateConverter = DateConverter()
    var progress = 0f
    lateinit var dashboardList:DashboardListViewModel
    lateinit var date: Date


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var binding:FragmentDashboardBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)


        var view = binding.root

        date = Date(dateConverter.getCurrentTimestamp() - dateConverter.convertDateToTimestamp(Data.date),Constants.timeConst.twentyOneDays)
        var mainProgress = MainProgressViewModel(date)

        if(Data.healthProgressViewList.isEmpty()) {
            prepareHealthProgressList()
        }

        dashboardList = DashboardListViewModel(ArrayList(), activity.applicationContext)
        var healthProgress = HealthProgressListViewModel(Data.healthProgressViewList, activity)


        dashboardList.list.add(mainProgress as Object)
        dashboardList.list.add(healthProgress as Object)


        Timer().scheduleAtFixedRate(timerTask{
            date = Date(dateConverter.getCurrentTimestamp() - dateConverter.convertDateToTimestamp(Data.date),Constants.timeConst.twentyOneDays)
            progress = (dateConverter.getCurrentTimestamp() - dateConverter.convertDateToTimestamp(Data.date)).toFloat()/(Constants.timeConst.twentyOneDays).toFloat() * 100f
            (dashboardList.list.get(0) as MainProgressViewModel).setProgress(date)
            updateHealthProgress()
            (dashboardList.list.get(1) as HealthProgressListViewModel).updateAll(Data.healthProgressViewList)
        },0,1000)

        binding.viewModel = dashboardList


        return view
    }



    fun prepareHealthProgressList() {
        var date: Date? = null
        var index = 0
        for (item in Data.healthProgressTimes) {
            date = Date(dateConverter.convertDateToTimestamp(Data.date) + item - dateConverter.getCurrentTimestamp(),item)
            Data.healthProgressViewList.add(HealthProgressViewModel(index, "Oxygen level", date, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s," +
                " when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting," +
                " remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing " +
                "software like Aldus PageMaker including versions of Lorem Ipsum.", activity))
            index++
        }
    }


    fun updateHealthProgress() {
        var index = 0
        for (item in Data.healthProgressTimes) {
            date = Date(dateConverter.convertDateToTimestamp(Data.date) + item - dateConverter.getCurrentTimestamp(),item)
            Data.healthProgressViewList[index].date.set(date)
            index++
        }
    }


}
