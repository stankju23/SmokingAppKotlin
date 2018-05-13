package com.example.stanislavcavajda.bakalarkasmokingapp.Cravings

import android.app.Fragment
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.DateConverter
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.GPSTracker
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.RealmDatabase.RealmDB
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.FragmentCravingBinding
import com.google.android.gms.common.api.GoogleApiClient
import java.util.UUID



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CravingFragment : Fragment() {


    var cravingBtnAnimation: AnimationDrawable? = null
    lateinit var gpsTracker:GPSTracker
    var dateConverter = DateConverter()
    lateinit var binding:FragmentCravingBinding

    lateinit var mGoogleApiClient: GoogleApiClient

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_craving, container, false)

        var view = binding.root



       gpsTracker = GPSTracker(activity)
        var animation = AnimationUtils.loadAnimation(activity,R.anim.add_craving_animation)
        var cigaretteAnim = AnimationUtils.loadAnimation(activity,R.anim.alpha_anim)

        var imageView = view.findViewById<ImageView>(R.id.inside_oval)
        var cigarette = view.findViewById<ImageView>(R.id.cigarette)

        var showCravingBtn = view.findViewById<Button>(R.id.show_cravings)

        imageView.startAnimation(animation)
        cigarette.startAnimation(cigaretteAnim)

        imageView.setOnClickListener(View.OnClickListener {
            try {
                if (gpsTracker.canGetLocation) {
                    gpsTracker.getLocation()
                } else {
                    gpsTracker.showSettingsAlert()
                }
                var craving = Craving(UUID.randomUUID().toString(),dateConverter.getTime(),dateConverter.getDate(dateConverter.getCurrentTimestamp()),gpsTracker.latitude,gpsTracker.longitude,activity)
                Data.cravings.add(craving)
                RealmDB.saveCraving(craving)

                val addCraving = Intent(activity, AddCravingActivity::class.java)
                startActivity(addCraving)
            } catch (e:Exception) {

            }

        })

        showCravingBtn.setOnClickListener {

            var showCravings = Intent(activity,ShowCravings::class.java)
            startActivity(showCravings)
        }

        binding.cravingNumber = Data.cravings.size

        return view
    }

    override fun onResume() {
        binding.cravingNumber = Data.cravings.size
        super.onResume()
    }

    override fun onPause() {
        gpsTracker.stopUsingGPS()
        super.onPause()
    }
}
