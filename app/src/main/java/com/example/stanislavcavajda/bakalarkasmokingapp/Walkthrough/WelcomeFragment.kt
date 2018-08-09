package com.example.stanislavcavajda.bakalarkasmokingapp.Walkthrough


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.example.stanislavcavajda.bakalarkasmokingapp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [WelcomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class WelcomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_welcome, container, false)

        var nextButton = view.findViewById<Button>(R.id.next_button)
        nextButton.setOnClickListener {
            var fm = activity?.supportFragmentManager
            fm?.beginTransaction()?.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left)?.replace(R.id.container,WalkthroughFragment1())?.commit()

        }
        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment WelcomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = WelcomeFragment()
    }
}
