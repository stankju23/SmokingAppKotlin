package com.example.stanislavcavajda.bakalarkasmokingapp.journal

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.FragmentJournalBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [JournalFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class JournalFragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var binding:FragmentJournalBinding = FragmentJournalBinding.inflate(inflater, container,false)
        var view = binding.root

        var journalListViewModel = JournalListViewModel(Data.journalCardSList,activity)

        //var scrollView = view.findViewById<ScrollView>(R.id.journal_tab_scroll_view)

        //OverScrollDecoratorHelper.setUpOverScroll(scrollView)

        binding.journalViewModel = journalListViewModel
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment JournalFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = JournalFragment()

    }
}
