package com.example.stanislavcavajda.bakalarkasmokingapp.Walkthrough

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data

import com.example.stanislavcavajda.bakalarkasmokingapp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [WalkthroughFragment2.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class WalkthroughFragment2 : Fragment() {
    // TODO: Rename and change types of parameters

    var value = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_walkthrough_fragment2, container, false)
        var nextButton = view.findViewById<Button>(R.id.next_button)

        var actualValue = view.findViewById<EditText>(R.id.actual_value)
        actualValue.setText(value.toString())

        var plusBtn = view.findViewById<ImageButton>(R.id.plus_button)
        var minusBtn = view.findViewById<ImageButton>(R.id.minus_button)

        nextButton.setOnClickListener {
            Data.MoneyDashboard.cigarretesInPackage = this.value
            var fm = activity?.supportFragmentManager
            fm?.beginTransaction()?.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left)?.replace(R.id.container,WalkthroughFragment3())?.commit()

        }

        plusBtn.setOnClickListener {
            if (value <= 30) {
                value++
                actualValue.setText(value.toString())
            }
        }
        minusBtn.setOnClickListener {
            if (value >= 5) {
                value--
                actualValue.setText(value.toString())
            }
        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment WalkthroughFragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = WalkthroughFragment2()
    }
}
