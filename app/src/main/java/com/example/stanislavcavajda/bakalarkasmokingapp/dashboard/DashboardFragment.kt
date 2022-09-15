package com.example.stanislavcavajda.bakalarkasmokingapp.dashboard

import android.app.Fragment
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stanislavcavajda.bakalarkasmokingapp.dashboard.healthlist.HealthProgressListViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.dashboard.mainprogress.MainProgressViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.dashboard.moneysaved.MoneySavedViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.dashboard.wishmanager.WishListViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    lateinit var recycler: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var binding: FragmentDashboardBinding = FragmentDashboardBinding.inflate(inflater, container, false)

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
