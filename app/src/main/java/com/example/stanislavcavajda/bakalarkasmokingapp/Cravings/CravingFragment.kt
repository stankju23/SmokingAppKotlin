package com.example.stanislavcavajda.bakalarkasmokingapp.Cravings

import android.Manifest
import android.app.Fragment
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.DateConverter
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.RealmDatabase.RealmDB
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.fragment_craving.view.*
import java.util.UUID


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class CravingFragment : Fragment(),GoogleApiClient.OnConnectionFailedListener,GoogleApiClient.ConnectionCallbacks, LocationListener,com.google.android.gms.location.LocationListener {


    override fun onLocationChanged(location: Location?) {
        this.latitude = location?.latitude!!
        this.longitude = location?.longitude!!
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderEnabled(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderDisabled(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConnected(p0: Bundle?) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
             ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),100)
             startLocationServices()
        } else {
            startLocationServices()
        }

    }

    override fun onConnectionSuspended(p0: Int) {
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    var dateConverter = DateConverter()

    lateinit var mGoogleApiClient: GoogleApiClient

    var latitude = 0.0
    var longitude = 0.0

    lateinit var numberOfCraving:TextView
    var cravingCount = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_craving, container, false)


        mGoogleApiClient = GoogleApiClient.Builder(activity)
            .addConnectionCallbacks(this)
            .addApi(LocationServices.API)
            .build()
        mGoogleApiClient.connect()




        var animation = AnimationUtils.loadAnimation(activity,R.anim.add_craving_animation)
        var cigaretteAnim = AnimationUtils.loadAnimation(activity,R.anim.alpha_anim)

        var imageView = view.findViewById<ImageView>(R.id.inside_oval)
        var cigarette = view.findViewById<ImageView>(R.id.cigarette)

        var showCravingBtn = view.findViewById<Button>(R.id.show_cravings)

        imageView.startAnimation(animation)
        cigarette.startAnimation(cigaretteAnim)

        imageView.setOnClickListener(View.OnClickListener {
            if (latitude != 0.0 && longitude != 0.0) {
                if (Data.cravings.size == 0) {
                    var header = CravingHeader(UUID.randomUUID().toString(),dateConverter.getDate(dateConverter.getCurrentTimestamp()))
                    var craving = Craving(UUID.randomUUID().toString(), dateConverter.getTime(), dateConverter.getDate(dateConverter.getCurrentTimestamp()), latitude, longitude, activity)
                    Data.cravings.add(header)
                    RealmDB.saveHeader(header)
                    Data.cravings.add(craving)
                    RealmDB.saveCraving(craving)
                } else {
                    if (Data.cravings[Data.cravings.size - 1] is Craving && (Data.cravings[Data.cravings.size - 1] as Craving).date != dateConverter.getDate(dateConverter.getCurrentTimestamp())) {
                        var header = CravingHeader(UUID.randomUUID().toString(),dateConverter.getDate(dateConverter.getCurrentTimestamp()))
                        var craving = Craving(UUID.randomUUID().toString(), dateConverter.getTime(), dateConverter.getDate(dateConverter.getCurrentTimestamp()), latitude, longitude, activity)
                        Data.cravings.add(header)
                        RealmDB.saveHeader(header)
                        Data.cravings.add(craving)
                        RealmDB.saveCraving(craving)
                    } else {
                        var craving = Craving(UUID.randomUUID().toString(), dateConverter.getTime(), dateConverter.getDate(dateConverter.getCurrentTimestamp()), latitude, longitude, activity)
                        Data.cravings.add(craving)
                        RealmDB.saveCraving(craving)
                    }
                }



                val addCraving = Intent(activity, AddCravingActivity::class.java)
                startActivity(addCraving)
            }
        })

        showCravingBtn.setOnClickListener {

            var showCravings = Intent(activity,ShowCravings::class.java)
            startActivity(showCravings)
        }



        for (item in Data.cravings) {
            if (item is Craving) {
                cravingCount ++
            }
        }

        numberOfCraving = view.number_of_cravings
        numberOfCraving.text = "$cravingCount"
        return view
    }

    override fun onResume() {
        cravingCount = 0
        for (item in Data.cravings) {
            if (item is Craving) {
                cravingCount ++
            }
        }
        numberOfCraving.text = "$cravingCount"
        super.onResume()
    }

    override fun onStart() {
        mGoogleApiClient.connect()
        super.onStart()
    }

    override fun onStop() {
        mGoogleApiClient.disconnect()
        super.onStop()
    }


    fun startLocationServices() {

        var req = LocationRequest.create().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,req,this)

    }

//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>?, grantResults: IntArray?) {
//
//        when(requestCode) {
//            100 -> if (grantResults!![0] > 0 && grantResults!![0] == PackageManager.PERMISSION_GRANTED) {
//                startLocationServices()
//            }
//        }
//        if(permissions != null && grantResults != null) {
//            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        }
//
//    }

    override fun onPause() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient,this)
        super.onPause()
    }
}
