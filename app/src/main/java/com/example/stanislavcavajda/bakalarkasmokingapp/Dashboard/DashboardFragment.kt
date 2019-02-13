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
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager.WishListViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    lateinit var recycler: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var binding: FragmentDashboardBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)

        var view = binding.root

        binding.mainProgress = Data.dashboardList[0] as MainProgressViewModel
        binding.healthProgress = Data.dashboardList[1] as HealthProgressListViewModel
        binding.wishManager = Data.dashboardList[2] as WishListViewModel
        binding.moneySaved = Data.dashboardList[3] as MoneySavedViewModel

        Log.i("Dashboard fragment", "created")

        return view
    }

    override fun onDestroy() {
        Log.i("fragment", "destroyed")
        super.onDestroy()
    }

    override fun onStop() {
        super.onStop()
        Log.i("Dashboard fragment", "stopped")
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        Log.i("Thread", "resumed")
        super.onResume()
    }
}
