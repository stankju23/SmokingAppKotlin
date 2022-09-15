package com.example.stanislavcavajda.bakalarkasmokingapp.missions

import android.app.Fragment
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.FragmentMissionsBinding

class MissionsFragment : Fragment() {

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var binding:FragmentMissionsBinding = FragmentMissionsBinding.inflate(inflater)

        var view = binding.root

        var recycler = view.findViewById<RecyclerView>(R.id.missions_recycler_view)
        recycler.layoutManager =
            LinearLayoutManager(activity)
        recycler.setHasFixedSize(true)

        var missionViewModel = MissionListViewModel(Data.missionList,activity)

        binding.viewModel = missionViewModel

        recyclerView = binding.missionsRecyclerView

        return view
    }

    override fun onResume() {
        Log.i("resumed","fragment")
        if (recyclerView.adapter != null) {
            recyclerView.adapter?.notifyDataSetChanged()
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
