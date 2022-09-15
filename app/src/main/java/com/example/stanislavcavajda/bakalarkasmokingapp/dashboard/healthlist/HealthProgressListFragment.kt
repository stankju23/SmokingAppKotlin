package com.example.stanislavcavajda.bakalarkasmokingapp.dashboard.healthlist

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.FragmentHealthProgressListBinding

class HealthProgressListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        var binding: FragmentHealthProgressListBinding = FragmentHealthProgressListBinding.inflate(inflater, container, false)
        var view = binding.root

        var viewModel = HealthProgressListViewModel(Data.healthProgressViewList, activity)

        binding.viewModel = viewModel


        return view
    }
}
