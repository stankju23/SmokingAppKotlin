package com.example.stanislavcavajda.bakalarkasmokingapp.Walkthrough

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/**
 * A simple [Fragment] subclass.
 * Use the [WalkthroughFragment3.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class WalkthroughFragment3 : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_walkthrough_fragment3, container, false)

        var nextButton = view.findViewById<Button>(R.id.next_button)

        var actualValue = view.findViewById<EditText>(R.id.actual_value)

        nextButton.setOnClickListener {

            var packagePrice = actualValue.text.toString()
            if (packagePrice.length != 0) {
                Data.MoneyDashboard.packagePrice = packagePrice.toDouble()
                var fm = activity?.supportFragmentManager
                fm?.beginTransaction()?.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left)?.replace(R.id.container,WalkthroughFragment4())?.commit()
            } else {
                Toast.makeText(activity,R.string.error_price,Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment WalkthroughFragment3.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = WalkthroughFragment3()
    }
}
