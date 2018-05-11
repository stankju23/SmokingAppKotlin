package com.example.stanislavcavajda.bakalarkasmokingapp.Main

//import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.DateConverter
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.Cravings.CravingFragment
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.DashboardFragment
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.*
import com.example.stanislavcavajda.bakalarkasmokingapp.Missions.Activity
import com.example.stanislavcavajda.bakalarkasmokingapp.Missions.Mission
import com.example.stanislavcavajda.bakalarkasmokingapp.Missions.MissionsFragment
import com.example.stanislavcavajda.bakalarkasmokingapp.Model.Date
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.RealmDatabase.RealmDB
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityMainBinding
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var menu:Menu


    override fun onCreate(savedInstanceState: Bundle?) {

        var dateConverter = DateConverter()

        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this,Data.actualTheme)

        // var preferences = getSharedPreferences("date", Context.MODE_PRIVATE)
        //Data.date = preferences.getString(Constants.preferences.DATE_PREFERENCES,"03-02-2018")

        Realm.init(this)
        //Realm.deleteRealm(Realm.getDefaultConfiguration())
        if (Data.wishList.isEmpty()) {
            RealmDB.getWishes(this)
        }

        var binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        var toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.title = resources.getString(R.string.title_dashboard)

        var missionFragment:MissionsFragment? = null
        var cravingFragment:CravingFragment? = null


        var fragmentManager = fragmentManager
        var fragmenttransaction = fragmentManager.beginTransaction()

        for (i in 0 until fragmentManager.getBackStackEntryCount()) {
            fragmentManager.popBackStack()
        }

        if (fragmentManager.findFragmentByTag("dashboard") == null) {
            Log.i("fragment","not found")
            var dashboardFragment = DashboardFragment()
            fragmenttransaction.add(R.id.fragment_container, dashboardFragment).addToBackStack("dashboard").commit()
            navigation.selectedItemId = R.id.navigation_dasboard
        }

        navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
            var fragmenttransaction = fragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.navigation_dasboard -> {

                    // set tooblar
                    this.menu.findItem(R.id.settings).setIcon(R.drawable.ic_settings)
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    supportActionBar?.setDisplayShowHomeEnabled(true)
                    supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
                    supportActionBar?.title = resources.getString(R.string.title_dashboard)

                    fragmentManager.popBackStack()
                    fragmenttransaction.add(R.id.fragment_container, DashboardFragment()).addToBackStack("dashboard")
                    fragmenttransaction.commit()



                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_missions -> {
                    this.menu.findItem(R.id.settings).setIcon(R.drawable.ic_settings)
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.setDisplayShowHomeEnabled(false)
                    supportActionBar?.title = resources.getString(R.string.title_mission)

                    fragmentManager.popBackStack()
                    fragmenttransaction.replace(R.id.fragment_container, MissionsFragment()).addToBackStack("missions")
                    fragmenttransaction.commit()

                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_cravings -> {

                    this.menu.findItem(R.id.settings).setIcon(R.drawable.show_stats)

                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.setDisplayShowHomeEnabled(false)
                    supportActionBar?.title = resources.getString(R.string.title_cravings)

                    fragmentManager.popBackStack()
                    fragmenttransaction.replace(R.id.fragment_container, CravingFragment()).addToBackStack("missions")
                    fragmenttransaction.commit()


                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_infoarea -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    supportActionBar?.setDisplayShowHomeEnabled(true)
                    supportActionBar?.title = resources.getString(R.string.title_infoarea)

                    return@OnNavigationItemSelectedListener true
                }

            }
            true
        })

        BottomNavigationViewHelper.disableShiftMode(navigation)


        if (Data.missionList.isEmpty()) {
            RealmDB.getMissions(this)
        }


        if (Data.missionList.isEmpty()) {

            Data.objectives = JSONParser.parseObjectives(JSONParser.loadJsonFromAssets("Objectives", this), this)

            var missionList = ArrayList<Mission>()

            for (i in 1..21) {
                var activities = ArrayList<Activity>()
                Collections.shuffle(Data.objectives)
                for (i in 0..4) {
                    var activity = Activity(false, Data.objectives[i],UUID.randomUUID().toString())
                    activities.add(activity)
                }
                var date = Date(dateConverter.getCurrentTimestamp() - dateConverter.convertDateToTimestamp(Data.date), Constants.timeConst.twentyOneDays)
                if (i <= date.days) {
                    var mission = Mission(UUID.randomUUID().toString(),"MISSION $i", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(),Constants.timeConst.oneDay),false, false, activities,0)
                    missionList.add(mission)
                } else {
                    if (i == date.days.toInt() + 1) {
                        var mission = Mission(UUID.randomUUID().toString(),"MISSION $i", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(),Constants.timeConst.oneDay),false, false, activities,0)
                        missionList.add(mission)
                    } else {
                        var mission = Mission(UUID.randomUUID().toString(),"MISSION $i", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(),Constants.timeConst.oneDay),false, false, activities,0)
                        missionList.add(mission)
                    }
                }
            }
            Data.missionList.addAll(missionList)

            for (mission in Data.missionList) {
                RealmDB.addMission(mission)
            }

        }

        var timer = Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                for (i in 1..21) {
                    var date = Date(dateConverter.getCurrentTimestamp() - dateConverter.convertDateToTimestamp(Data.date), Constants.timeConst.twentyOneDays)
                    if (i <= date.days) {
                        var mission = Mission(Data.missionList[i - 1].id,"MISSION $i", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay),Date((dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay) + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(),Constants.timeConst.oneDay), false, false, Data.missionList[i - 1].activities!!,Data.missionList[i - 1].done)
                        Data.missionList[i - 1].setMission(mission)
                        Data.missionList[i - 1].getDone()
                    } else {
                        if (i == date.days.toInt() + 1) {
                            var mission = Mission(Data.missionList[i - 1].id,"MISSION $i", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay),Date(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(),Constants.timeConst.oneDay), false, true, Data.missionList[i - 1].activities!!,Data.missionList[i - 1].done)
                            Data.missionList[i - 1].setMission(mission)
                            Data.missionList[i - 1].getDone()
                            //Log.i(Data.missionList[i - 1].name ,  "${Data.missionList[i - 1].observableCompletionDate?.get()?.hours}  ${Data.missionList[i - 1].observableCompletionDate?.get()?.minutes} ${Data.missionList[i - 1].observableCompletionDate?.get()?.seconds}")
                            Log.i("MISSION",Data.missionList[i - 1].completionTime.get())
                        } else {
                            var mission = Mission(Data.missionList[i - 1].id,"MISSION $i", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay),Date(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(),Constants.timeConst.oneDay), true, false, Data.missionList[i - 1].activities!!,Data.missionList[i - 1].done)
                            Data.missionList[i - 1].setMission(mission)
                            Data.missionList[i - 1].getDone()
                        }
                    }
                }
                //Log.i("Missions","Updated")
            }
        }, 0, 1000)

    }

    override fun onBackPressed() {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        this.menu = menu!!
        menuInflater.inflate(R.menu.action_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                var intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.settings -> {
                if (navigation.selectedItemId == R.id.navigation_dasboard){
                    var intent = Intent(this, ChangeColor::class.java)
                    startActivity(intent)
                } else {

                }

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRestart() {
        Data.themeChanged = ThemeManager.recreateActivity(this,Data.themeChanged,Data.actualTheme)
        super.onRestart()
    }

}
