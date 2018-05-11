package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.HealthList

import android.app.Fragment
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.FragmentHealthProgressListBinding

class HealthProgressListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var binding: FragmentHealthProgressListBinding = DataBindingUtil.inflate(inflater!!, R.layout.fragment_health_progress_list, container, false)
        var view = binding.root

        var viewModel = HealthProgressListViewModel(Data.healthProgressViewList, activity)

        binding.viewModel = viewModel


        return view
    }
}
