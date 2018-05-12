package com.example.stanislavcavajda.bakalarkasmokingapp.Cravings

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.GPSTracker
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.Koloda.KolodaAdapter
import com.example.stanislavcavajda.bakalarkasmokingapp.Koloda.KolodaClass
import com.example.stanislavcavajda.bakalarkasmokingapp.Koloda.KolodaItem
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationServices
import com.mindorks.placeholderview.SwipeDecor
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.SwipeViewBuilder
import kotlinx.android.synthetic.main.activity_add_craving.*
import java.util.*
import kotlin.collections.ArrayList


class AddCraving : AppCompatActivity() {

    lateinit var googleApiClient: GoogleApiClient

    private var adapter: KolodaAdapter? = null
    var latitude:Double = 0.0
    var longitude:Double = 0.0

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var locationCallback: LocationCallback

    lateinit var gpsTracker:GPSTracker


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.setTheme(this, Data.actualTheme)
        setContentView(R.layout.activity_add_craving)

        gpsTracker =  GPSTracker(this)
        if (gpsTracker.canGetLocation) {
            gpsTracker.getLocation()
            Toast.makeText(this, getCityName(gpsTracker.latitude,gpsTracker.longitude), Toast.LENGTH_SHORT).show()
        } else {
            gpsTracker.showSettingsAlert()
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


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

    fun showToast(){
        Toast.makeText(this,getCityName(latitude,longitude),Toast.LENGTH_SHORT).show()
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
        super.onStart()

    }

    override fun onResume() {

        super.onResume()
    }

    override fun onPause() {
        gpsTracker.stopUsingGPS()
        super.onPause()
    }

    override fun onStop()
    {

        super.onStop()
    }

    fun getCityName(lat:Double, long:Double):String {
        var geocoder = Geocoder(this, Locale.getDefault())
        var list = ArrayList<Address>()
        list.addAll(geocoder.getFromLocation(lat,long,1))
        var city = ""
        try {
            city = list[0].locality + "," +list[0].thoroughfare + "," +list[0].featureName
        } catch (e:Exception) {

        }

        return city
    }
}
