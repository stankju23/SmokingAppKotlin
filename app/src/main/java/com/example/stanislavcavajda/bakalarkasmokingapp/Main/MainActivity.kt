package com.example.stanislavcavajda.bakalarkasmokingapp.Main

//import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.DateConverter
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.DashboardFragment
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.BottomNavigationViewHelper
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.DateConverter
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.RealmDB
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityMainBinding
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {

        var dateConverter = DateConverter()

        super.onCreate(savedInstanceState)


       // var preferences = getSharedPreferences("date", Context.MODE_PRIVATE)
        //Data.date = preferences.getString(Constants.preferences.DATE_PREFERENCES,"03-02-2018")

        Realm.init(this)
        //Realm.deleteRealm(Realm.getDefaultConfiguration())
        RealmDB.getWishes(this)

        var binding: ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        var toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.title = resources.getString(R.string.title_dashboard)



        navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_dasboard -> {

                    // set tooblar
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    supportActionBar?.setDisplayShowHomeEnabled(true)
                    supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
                    supportActionBar?.title = resources.getString(R.string.title_dashboard)

                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_missions -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.setDisplayShowHomeEnabled(false)
                    supportActionBar?.title = resources.getString(R.string.title_mission)

                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_cravings -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.setDisplayShowHomeEnabled(false)
                    supportActionBar?.title = resources.getString(R.string.title_cravings)

                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_infoarea -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.setDisplayShowHomeEnabled(false)
                    supportActionBar?.title = resources.getString(R.string.title_infoarea)

                    return@OnNavigationItemSelectedListener true
                }

            }
            true
        })


        var fragmentManager = fragmentManager
        var fragmenttransaction = fragmentManager.beginTransaction()
        fragmenttransaction.replace(R.id.fragment_container, DashboardFragment()).addToBackStack("dashboard").commit()

        BottomNavigationViewHelper.disableShiftMode(navigation)


    }

    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                var intent = Intent(this,SettingsActivity::class.java)
                startActivity(intent)
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }


}
