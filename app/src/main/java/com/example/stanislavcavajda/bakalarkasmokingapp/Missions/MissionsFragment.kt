package com.example.stanislavcavajda.bakalarkasmokingapp.Missions

import android.app.Fragment
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.FragmentMissionsBinding

class MissionsFragment : Fragment() {

    lateinit var recyclerView:RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var binding:FragmentMissionsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_missions, container, false)

        var view = binding.root

        var recycler = view.findViewById<RecyclerView>(R.id.missions_recycler_view)
        recycler.setHasFixedSize(true)


        var missionViewModel = MissionListViewModel(Data.missionList,activity)

        binding.viewModel = missionViewModel

        recyclerView = view.findViewById(R.id.missions_recycler_view)

        return view
    }

    override fun onResume() {
        Log.i("resumed","fragment")
        if (recyclerView.adapter != null) {
            recyclerView.adapter.notifyDataSetChanged()
        }
        super.onResume()
    }

    fun scrollToPosition(recyclerView: RecyclerView) {
        var index = 0
        for (i in 0..Data.missionList.size - 1) {
            if (Data.missionList[i].available.get()) {
                index = i
            }
        }

        recyclerView.scrollToPosition(index)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
