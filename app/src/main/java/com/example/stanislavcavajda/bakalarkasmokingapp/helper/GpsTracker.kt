package com.example.stanislavcavajda.bakalarkasmokingapp.helper

/**
 * Created by stanislavcavajda on 12/05/2018.
 */

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log

class GPSTracker(private val mContext: Context) : LocationListener {

    // flag for GPS status
    var isGPSEnabled = false

    // flag for network status
    internal var isNetworkEnabled = false

    // flag for GPS status
    internal var canGetLocation = false

    internal var location: Location? = null // location
    internal var latitude: Double = 0.toDouble() // latitude
    internal var longitude: Double = 0.toDouble() // longitude

    // Declaring a Location Manager
    protected var locationManager: LocationManager? = null

    init {
        getLocation()
    }

            @SuppressLint("MissingPermission")
                    /**
     * Function to get the user's current location
     *
     * @return
     */
    fun getLocation(): Location? {
        try {
            locationManager = mContext
                    .getSystemService(Context.LOCATION_SERVICE) as LocationManager

            // getting GPS status
            isGPSEnabled = locationManager!!
                    .isProviderEnabled(LocationManager.GPS_PROVIDER)

            Log.v("isGPSEnabled", "=" + isGPSEnabled)

            // getting network status
            isNetworkEnabled = locationManager!!
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            Log.v("isNetworkEnabled", "=" + isNetworkEnabled)

            if (isGPSEnabled == false && isNetworkEnabled == false) {
                // no network provider is enabled
            } else {
                this.canGetLocation = true
                if (isNetworkEnabled) {
                    location = null
                    locationManager!!.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this)
                    Log.d("Network", "Network")
                    if (locationManager != null) {
                        location = locationManager!!
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                        if (location != null) {
                            latitude = location!!.latitude
                            longitude = location!!.longitude
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    location = null
                    if (location == null) {
                        locationManager!!.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES.toFloat(), this)
                        Log.d("GPS Enabled", "GPS Enabled")
                        if (locationManager != null) {
                            location = locationManager!!
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER)
                            if (location != null) {
                                latitude = location!!.latitude
                                longitude = location!!.longitude
                            }
                        }
                    }
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return location
    }

    /**
     * Stop using GPS listener Calling this function will stop using GPS in your
     * app
     */
    fun stopUsingGPS() {
        if (locationManager != null) {
            locationManager!!.removeUpdates(this@GPSTracker)
        }
    }

    /**
     * Function to get latitude
     */
    fun getLatitude(): Double {
        if (location != null) {
            latitude = location!!.latitude
        }

        // return latitude
        return latitude
    }

    /**
     * Function to get longitude
     */
    fun getLongitude(): Double {
        if (location != null) {
            longitude = location!!.longitude
        }

        // return longitude
        return longitude
    }

    /**
     * Function to check GPS/wifi enabled
     *
     * @return boolean
     */
    fun canGetLocation(): Boolean {
        return this.canGetLocation
    }

    /**
     * Function to show settings alert dialog On pressing Settings button will
     * lauch Settings Options
     */
    fun showSettingsAlert() {
        val alertDialog = AlertDialog.Builder(mContext,android.R.style.Theme_Material_Dialog_Alert)

        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings")

        // Setting Dialog Message
        alertDialog
                .setMessage("GPS is not enabled. Do you want to go to settings menu?")

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings"
        ) { dialog, which ->
            val intent = Intent(
                    Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            mContext.startActivity(intent)
        }

        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel"
        ) { dialog, which -> dialog.cancel() }

        // Showing Alert Message
        alertDialog.show()
    }

    override fun onLocationChanged(location: Location) {}

    override fun onProviderDisabled(provider: String) {}

    override fun onProviderEnabled(provider: String) {}

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}

    companion object {

        // The minimum distance to change Updates in meters
        private val MIN_DISTANCE_CHANGE_FOR_UPDATES: Long = 1 // 10 meters

        // The minimum time between updates in milliseconds
        private val MIN_TIME_BW_UPDATES: Long = 1 // 1 minute
    }

}