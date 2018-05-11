package com.example.stanislavcavajda.bakalarkasmokingapp.Cravings

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.Koloda.KolodaAdapter
import com.example.stanislavcavajda.bakalarkasmokingapp.Koloda.KolodaClass
import com.example.stanislavcavajda.bakalarkasmokingapp.Koloda.KolodaItem
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import com.mindorks.placeholderview.SwipeDecor
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.SwipeViewBuilder
import kotlinx.android.synthetic.main.activity_add_craving.*
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList


class AddCraving : AppCompatActivity(), GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    override fun onConnectionFailed(p0: ConnectionResult?) {

    }

    override fun onConnected(p0: Bundle?) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            var lastLocation = LocationServices.FusedLocationApi?.getLastLocation(googleApiClient);
            try {
                latitude = lastLocation?.getLatitude()!!
                longitude = lastLocation?.getLongitude()!!
            } catch (e: Exception) {

            }

        }

        Toast.makeText(this,getCityName(latitude,longitude),Toast.LENGTH_LONG).show()
        Log.i("latitude",latitude.toString())
        Log.i("longitude",longitude.toString())

    }

    override fun onConnectionSuspended(p0: Int) {

    }

    lateinit var googleApiClient: GoogleApiClient


    private var adapter: KolodaAdapter? = null
    var latitude:Double = 0.0
    var longitude:Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.setTheme(this, Data.actualTheme)
        setContentView(R.layout.activity_add_craving)


        googleApiClient = GoogleApiClient
                .Builder(this,this,this)
                .addApi(LocationServices.API)
                .build()






        var toolbar = findViewById<Toolbar>(R.id.add_craving_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_cancel)
        supportActionBar?.title = resources.getString(R.string.first_aid)


        var swipeCard = findViewById<SwipePlaceHolderView>(R.id.swipe_card)

        swipeCard.getBuilder<SwipePlaceHolderView,SwipeViewBuilder<SwipePlaceHolderView>>()
                .setDisplayViewCount(3)
                .setSwipeDecor(SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.swipe_in_message)
                        .setSwipeOutMsgLayoutId(R.layout.swipe_out_message))


        for (i in 0..5) {
            var kolodaItem = KolodaClass(this, KolodaItem(resources.getDrawable(R.drawable.koloda_item1),"Going out and running", "adsjvdvjashjvdasdhasjvhdvhjashjvgdhjvasjvhdjvghasjvgdasgdjvghasdjgvjadvascfjasdfhahsdjashdcjascjhasfcjasfc"),swipeCard)
            swipeCard.addView(kolodaItem)
        }

        swipeCard.addItemRemoveListener { if (it == 0) { onBackPressed() } }

        like.setOnClickListener {
            swipeCard.doSwipe(true)

        }

        dislike.setOnClickListener {
            swipeCard.doSwipe(false)
        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_craving, menu)
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.repeat -> {
                recreate()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        if (googleApiClient != null) {
            googleApiClient.connect()
        }
        super.onStart()
    }

    override fun onStop() {
        googleApiClient.disconnect()
        super.onStop()
    }

    fun getCityName(lat:Double, long:Double):String {
        var geocoder = Geocoder(this, Locale.getDefault())
        var list = ArrayList<Address>()
        list.addAll(geocoder.getFromLocation(lat,long,1))
        return list[0].locality
    }

}
