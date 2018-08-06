package com.example.stanislavcavajda.bakalarkasmokingapp.Cravings

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager

class CravingMapActivity : AppCompatActivity(), OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    lateinit var mClusterManager :ClusterManager<Craving>

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

        var toolbar = findViewById<Toolbar>(R.id.map_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)
        supportActionBar?.title = resources.getString(R.string.map_of_cravings)


        mMap = googleMap

        var options = MarkerOptions()

        var latlngList = ArrayList<LatLng>()

        var markers = ArrayList<LatLng>()

        var cravings = ArrayList<CravingItem>()

        cravings.addAll(Data.cravings)

        mClusterManager = ClusterManager<Craving>(this,mMap)

        mMap.setOnCameraIdleListener(mClusterManager)
        mMap.setOnMarkerClickListener(mClusterManager)

        mClusterManager.renderer = CravingMarkerRenderer(mClusterManager,this,mMap)

        for (i in cravings) {
            if (i is Craving) {
                mClusterManager.addItem(i)
            }
        }

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng((cravings[cravings.size - 1] as Craving).latitude,(cravings[cravings.size - 1] as Craving).longitude),16f))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.show_list -> {
                var cravingList = Intent(this,ShowCravings::class.java)
                startActivity(cravingList)

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        if (marker != null) {
            marker.showInfoWindow()
            return true
        }
        return false
    }
}
