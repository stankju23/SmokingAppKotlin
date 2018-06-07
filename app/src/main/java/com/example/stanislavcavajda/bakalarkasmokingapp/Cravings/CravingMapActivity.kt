package com.example.stanislavcavajda.bakalarkasmokingapp.Cravings

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions



class CravingMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this,Data.actualTheme)
        setContentView(R.layout.activity_craving_map)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap

        var options = MarkerOptions()

        var latlngList = ArrayList<LatLng>()

        var markers = ArrayList<LatLng>()

        var cravings = ArrayList<CravingItem>()

        cravings.addAll(Data.cravings)

//        val sortedList = cravings.sortedWith(compareBy({ it.address }))
//        var index = 0
//        for (item in sortedList) {
//
//
//            if (index != 0) {
//                if (item.address != Data.cravings[index - 1].address) {
//                    var city = LatLng(item.latitude, item.longitude)
//                    markers.add(city)
//                }
//            } else {
//                var city = LatLng(item.latitude, item.longitude)
//                markers.add(city)
//            }
//
//            index++
//        }

        for (i in cravings) {
            if (i is Craving) {
                var city = LatLng(i.latitude, i.longitude)
                markers.add(city)
            }
        }

        val icon = BitmapFactory.decodeResource(getResources(),R.drawable.marker)

        for (city in markers) {
            options.position(city)
            options.icon(BitmapDescriptorFactory.fromBitmap(icon))
            mMap.addMarker(options)
        }

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markers[markers.size - 1],  16f))

    }
}
