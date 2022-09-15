package com.example.stanislavcavajda.bakalarkasmokingapp.walkthrough

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.DateConverter
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.JSONParser
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.NotificationScheduler
import com.example.stanislavcavajda.bakalarkasmokingapp.journal.Journal
import com.example.stanislavcavajda.bakalarkasmokingapp.koloda.KolodaItem
import com.example.stanislavcavajda.bakalarkasmokingapp.main.MainActivity
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.realmdatabase.RealmDB
import java.text.SimpleDateFormat
import java.util.Date
import java.util.UUID

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/**
 * A simple [Fragment] subclass.
 * Use the [WalkthroughFragment4.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class WalkthroughFragment4 : Fragment() {
    // TODO: Rename and change types of parameters

    var dateConverter = DateConverter()
    var notificationScheduler = NotificationScheduler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_walkthrough_fragment4, container, false)

        var nextButton = view.findViewById<Button>(R.id.next_button)

        var currencySpinner = view.findViewById<Spinner>(R.id.currency_spinner)

        var adapter = ArrayAdapter<String>(requireContext(),android.R.layout.simple_dropdown_item_1line, JSONParser.parseCurrencies(activity!!))
        currencySpinner.adapter = adapter

        nextButton.setOnClickListener {
            if (activity != null) {

                nextButton.isEnabled = false

                val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                val actualDate = sdf.format(Date())

                var currency = currencySpinner.selectedItem
                Data.MoneyDashboard.currency = currency.toString()
                var preferences = activity?.getSharedPreferences("walkthrough", Context.MODE_PRIVATE)
                var editor = preferences?.edit()
                Data.firstTime = true
                editor?.putBoolean(Constants.preferences.FIRST_TIME,Data.firstTime)
                editor?.commit()

                var actualStatePreferences = activity?.getSharedPreferences("actualState",Context.MODE_PRIVATE)
                var actualStateEditor = actualStatePreferences?.edit()

                actualStateEditor?.putInt(Constants.actualState.CIGARETTES_PER_DAY, Data.MoneyDashboard.cigarretesPerDay)
                actualStateEditor?.putInt(Constants.actualState.CIGARETTES_IN_PACKAGE, Data.MoneyDashboard.cigarretesInPackage)
                actualStateEditor?.putFloat(Constants.actualState.PACKAGE_PRICE, Data.MoneyDashboard.packagePrice.toFloat())
                actualStateEditor?.putString(Constants.actualState.CURRENCY, Data.MoneyDashboard.currency)
                actualStateEditor?.putString(Constants.actualState.ACTUAL_DATE,actualDate)
                actualStateEditor?.commit()

                Data.date = actualDate

                for (i in 0..3) {
                    var kolodaItem = KolodaItem(UUID.randomUUID().toString(),i,"asdasmd","adsjvdvjashjvdasdhasjvhdvhjashjvgdhjvasjvhdjvghasjvgdasgdjvghasdjgvjadvascfjasdfhahsdjashdcjascjhasfcjasfc", activity!!)
                    Data.cravingsCardList.add(kolodaItem)
                    RealmDB.addCard(kolodaItem)
                }

                for (i in 1..21) {
                    notificationScheduler.scheduleNotification("$i ${activity?.resources?.getString(R.string.days)}","Vydrzali ste $i den nefajcit",R.drawable.achievment_mission_twenty,activity!!,i,i * Constants.timeConst.oneDay,false,false)
                }

                var currentTimestamp = dateConverter.getCurrentTimestamp()
                for (i in 1..21) {
                    var journal = Journal(i.toString(),"","", currentTimestamp + (i-1)*Constants.timeConst.oneDay,activity!!)
                    RealmDB.addJournal(journal)
                    Data.journalCardSList.add(journal)
                }

                var mainActivity = Intent(activity,MainActivity::class.java)
                activity?.startActivity(mainActivity)
            }

        }
        return view
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = WalkthroughFragment4()

    }
}
