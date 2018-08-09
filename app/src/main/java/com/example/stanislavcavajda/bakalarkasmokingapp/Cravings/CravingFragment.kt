package com.example.stanislavcavajda.bakalarkasmokingapp.Cravings

import android.Manifest
import android.app.Fragment
import android.content.Intent
import android.content.pm.PackageManager
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
import android.widget.Toast
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.DateConverter
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.RealmDatabase.RealmDB
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import java.util.UUID


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 *
 */
class CravingFragment : Fragment(),GoogleApiClient.OnConnectionFailedListener,GoogleApiClient.ConnectionCallbacks, LocationListener,com.google.android.gms.location.LocationListener {


    override fun onLocationChanged(location: Location?) {
        if(location != null) {
            this.latitude = location.latitude
            this.longitude = location.longitude
        }
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderEnabled(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderDisabled(p0: String?) {
        Toast.makeText(activity,"Turn on GPS location",Toast.LENGTH_SHORT).show()
    }

    override fun onConnected(p0: Bundle?) {
        startLocationServices()
    }

    override fun onConnectionSuspended(p0: Int) {
    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }


    var dateConverter = DateConverter()

    lateinit var mGoogleApiClient: GoogleApiClient

    var latitude = 0.0
    var longitude = 0.0


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
            try {
                if (latitude != null && longitude != null) {
                    if (Data.cravings.size == 0) {
                        var header = CravingHeader(UUID.randomUUID().toString(), dateConverter.getDate(dateConverter.getCurrentTimestamp()))
                        var craving = Craving(UUID.randomUUID().toString(), dateConverter.getTime(), dateConverter.getDate(dateConverter.getCurrentTimestamp()), latitude, longitude, activity, false)
                        Data.cravings.add(header)
                        RealmDB.saveHeader(header)
                        Data.cravings.add(craving)
                        RealmDB.saveCraving(craving)
                    } else {
                        if (Data.cravings[Data.cravings.size - 1] is Craving && (Data.cravings[Data.cravings.size - 1] as Craving).date != dateConverter.getDate(dateConverter.getCurrentTimestamp())) {
                            var header = CravingHeader(UUID.randomUUID().toString(), dateConverter.getDate(dateConverter.getCurrentTimestamp()))
                            var craving: Craving
                            if ((Data.cravings[Data.cravings.size - 1] as Craving).blackBG) {
                                craving = Craving(UUID.randomUUID().toString(), dateConverter.getTime(), dateConverter.getDate(dateConverter.getCurrentTimestamp()), latitude, longitude, activity, false)
                            } else {
                                craving = Craving(UUID.randomUUID().toString(), dateConverter.getTime(), dateConverter.getDate(dateConverter.getCurrentTimestamp()), latitude, longitude, activity, true)
                            }
                            Data.cravings.add(header)
                            RealmDB.saveHeader(header)
                            Data.cravings.add(craving)
                            RealmDB.saveCraving(craving)
                        } else {
                            var craving: Craving
                            if ((Data.cravings[Data.cravings.size - 1] as Craving).blackBG) {
                                craving = Craving(UUID.randomUUID().toString(), dateConverter.getTime(), dateConverter.getDate(dateConverter.getCurrentTimestamp()), latitude, longitude, activity, false)
                            } else {
                                craving = Craving(UUID.randomUUID().toString(), dateConverter.getTime(), dateConverter.getDate(dateConverter.getCurrentTimestamp()), latitude, longitude, activity, true)
                            }
                            Data.cravings.add(craving)
                            RealmDB.saveCraving(craving)
                        }
                    }

                    val addCraving = Intent(activity, AddCravingActivity::class.java)
                    startActivity(addCraving)
                }
            } catch (e:Exception) {
//                var craving:Craving
//                if((Data.cravings[Data.cravings.size - 1] as Craving).blackBG)
//                craving = Craving(UUID.randomUUID().toString(), dateConverter.getTime(), dateConverter.getDate(dateConverter.getCurrentTimestamp()), latitude, longitude, activity, true)
//                Data.cravings.add(craving)
//                RealmDB.saveCraving(craving)
//                val addCraving = Intent(activity, AddCravingActivity::class.java)
//                startActivity(addCraving)
//                Log.i("Error", e.message)
            }

        })

        showCravingBtn.setOnClickListener {

            var showCravings = Intent(activity,CravingMapActivity::class.java)
            startActivity(showCravings)
        }



//        for (item in Data.cravings) {
//            if (item is Craving) {
//                cravingCount ++
//            }
//        }
//
//        numberOfCraving = view.number_of_cravings
//        numberOfCraving.text = "$cravingCount"
        return view
    }

    override fun onResume() {
//        cravingCount = 0
//        for (item in Data.cravings) {
//            if (item is Craving) {
//                cravingCount ++
//            }
//        }
//        numberOfCraving.text = "$cravingCount"
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
        req.interval = 100
        req.fastestInterval = 50
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, req, this)
        } else {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),100)
        }

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
        if (mGoogleApiClient.isConnected) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this)
        }
        super.onPause()
    }
}
