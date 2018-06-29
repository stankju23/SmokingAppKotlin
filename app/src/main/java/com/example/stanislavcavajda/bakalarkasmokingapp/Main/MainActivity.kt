package com.example.stanislavcavajda.bakalarkasmokingapp.Main

//import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.DateConverter
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
//import com.example.stanislavcavajda.bakalarkasmokingapp.Cravings.CravingFragment
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.DashboardFragment
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.BottomNavigationViewHelper
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.DateConverter
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.JSONParser
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.Missions.Activity
import com.example.stanislavcavajda.bakalarkasmokingapp.Missions.Mission
import com.example.stanislavcavajda.bakalarkasmokingapp.Missions.MissionsFragment
import com.example.stanislavcavajda.bakalarkasmokingapp.Model.Date
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.RealmDatabase.RealmDB
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityMainBinding
import com.yarolegovich.slidingrootnav.SlideGravity
import com.yarolegovich.slidingrootnav.SlidingRootNav
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Collections
import java.util.Timer
import java.util.TimerTask
import java.util.UUID

class MainActivity : AppCompatActivity() {

    lateinit var menu: Menu
    lateinit var achievmentDrawer: SlidingRootNav
    val REQUEST_PERMISSION_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {

        var dateConverter = DateConverter()

        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this, Data.actualTheme)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_PERMISSION_CODE)
        }

        // var preferences = getSharedPreferences("date", Context.MODE_PRIVATE)
        //Data.date = preferences.getString(Constants.preferences.DATE_PREFERENCES,"03-02-2018")

        Realm.init(this)
        //Realm.deleteRealm(Realm.getDefaultConfiguration())
        if (Data.wishList.isEmpty()) {
            RealmDB.getWishes(this)
        }

        if (Data.cravings.isEmpty()) {
            RealmDB.getCravings(this)
        }

        var binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        var slidingView = SlidingRootNavBuilder(this)
            .withDragDistance(220)
            .withMenuLayout(R.layout.achievments_drawer_layout)
            .withGravity(SlideGravity.LEFT)
            .withRootViewScale(1f)
        achievmentDrawer = slidingView.inject()

        var achievmentRecyclerView = findViewById<RecyclerView>(R.id.achievment_list)
        achievmentRecyclerView.layoutManager = LinearLayoutManager(this)


        if (Data.achievmentList.size == 0) {
            for (i in 1..11) {
                Data.achievmentList.add(Achievment(resources.getDrawable(R.drawable.achievment), "END", "dasdasdasd",dateConverter.convertDateToTimestamp(Data.date) + i * Constants.timeConst.oneDay, false))
            }
        }

        var achievmentAdapter = AchievmentAdapter(Data.achievmentList,this)
        achievmentRecyclerView.adapter = achievmentAdapter

        var toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.title = resources.getString(R.string.title_dashboard)

        var fragmentManager = getFragmentManager()


        fragmentManager.beginTransaction().add(R.id.fragment_container, DashboardFragment(), "dashboard").commit()

        navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {

            when (it.itemId) {

                R.id.navigation_dasboard -> {
                    this.achievmentDrawer.isMenuLocked = false
                    // set tooblar
                    this.menu.findItem(R.id.settings).setIcon(R.drawable.ic_settings)
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    supportActionBar?.setDisplayShowHomeEnabled(true)
                    supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
                    supportActionBar?.title = resources.getString(R.string.title_dashboard)
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, DashboardFragment(), "dashboard").commit()
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_missions -> {
                    this.achievmentDrawer.isMenuLocked = true
                    if (this.achievmentDrawer.isMenuOpened) {
                        this.achievmentDrawer.closeMenu(true)
                    }
                    this.menu.findItem(R.id.settings).setIcon(R.drawable.ic_settings)
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.setDisplayShowHomeEnabled(false)
                    supportActionBar?.title = resources.getString(R.string.title_mission)

                    fragmentManager.beginTransaction().replace(R.id.fragment_container, MissionsFragment(), "missions").commit()
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_cravings -> {

                    this.achievmentDrawer.isMenuLocked = true
                    if (this.achievmentDrawer.isMenuOpened) {
                        this.achievmentDrawer.closeMenu(true)
                    }

                    this.menu.findItem(R.id.settings).setIcon(R.drawable.show_stats)

                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.setDisplayShowHomeEnabled(false)
                    supportActionBar?.title = resources.getString(R.string.title_cravings)
//                    fragmentManager.beginTransaction().replace(R.id.fragment_container, CravingFragment(), "cravings").commit()
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_infoarea -> {

                    this.achievmentDrawer.isMenuLocked = true

                    if (this.achievmentDrawer.isMenuOpened) {
                        this.achievmentDrawer.closeMenu(true)
                    }
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
                    var activity = Activity(false, Data.objectives[i], UUID.randomUUID().toString())
                    activities.add(activity)
                }
                var date = Date(dateConverter.getCurrentTimestamp() - dateConverter.convertDateToTimestamp(Data.date), Constants.timeConst.twentyOneDays)
                if (i <= date.days) {
                    var mission = Mission(UUID.randomUUID().toString(), "MISSION $i", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(), Constants.timeConst.oneDay), false, false, activities, 0)
                    missionList.add(mission)
                } else {
                    if (i == date.days.toInt() + 1) {
                        var mission = Mission(UUID.randomUUID().toString(), "MISSION $i", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(), Constants.timeConst.oneDay), false, false, activities, 0)
                        missionList.add(mission)
                    } else {
                        var mission = Mission(UUID.randomUUID().toString(), "MISSION $i", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(), Constants.timeConst.oneDay), false, false, activities, 0)
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
                        var mission = Mission(Data.missionList[i - 1].id, "MISSION $i", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date((dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay) + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(), Constants.timeConst.oneDay), false, false, Data.missionList[i - 1].activities!!, Data.missionList[i - 1].done)
                        Data.missionList[i - 1].setMission(mission)
                        Data.missionList[i - 1].getDone()
                    } else {
                        if (i == date.days.toInt() + 1) {
                            var mission = Mission(Data.missionList[i - 1].id, "MISSION $i", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(), Constants.timeConst.oneDay), false, true, Data.missionList[i - 1].activities!!, Data.missionList[i - 1].done)
                            Data.missionList[i - 1].setMission(mission)
                            Data.missionList[i - 1].getDone()
                            //Log.i(Data.missionList[i - 1].name ,  "${Data.missionList[i - 1].observableCompletionDate?.get()?.hours}  ${Data.missionList[i - 1].observableCompletionDate?.get()?.minutes} ${Data.missionList[i - 1].observableCompletionDate?.get()?.seconds}")
                            Log.i("MISSION", Data.missionList[i - 1].completionTime.get())
                        } else {
                            var mission = Mission(Data.missionList[i - 1].id, "MISSION $i", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(), Constants.timeConst.oneDay), true, false, Data.missionList[i - 1].activities!!, Data.missionList[i - 1].done)
                            Data.missionList[i - 1].setMission(mission)
                            Data.missionList[i - 1].getDone()
                        }
                    }
                }
                //Log.i("Missions","Updated")
            }
        }, 0, 1000)

        var achievmentTimer = Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                for (item in Data.achievmentList) {
                    if (item.endTimestamp <= dateConverter.getCurrentTimestamp()) {
                        item.isComplete = true
                        runOnUiThread {
                            achievmentAdapter.notifyDataSetChanged()
                        }
                    }
                }
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
                if (achievmentDrawer.isMenuOpened) {
                    achievmentDrawer.closeMenu(true)
                } else {
                    achievmentDrawer.openMenu(true)
                }
                return true
            }
            R.id.settings -> {
                if (navigation.selectedItemId == R.id.navigation_dasboard) {
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
        Data.themeChanged = ThemeManager.recreateActivity(this, Data.themeChanged, Data.actualTheme)
        super.onRestart()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_PERMISSION_CODE -> if (grantResults!![0] > 0 && grantResults!![0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                System.exit(0)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
