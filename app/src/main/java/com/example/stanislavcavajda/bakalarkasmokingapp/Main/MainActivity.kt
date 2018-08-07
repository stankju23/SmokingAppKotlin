package com.example.stanislavcavajda.bakalarkasmokingapp.Main

//import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.DateConverter
//import com.example.stanislavcavajda.bakalarkasmokingapp.Cravings.CravingFragment
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.Cravings.CravingFragment
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
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Collections
import java.util.Timer
import java.util.TimerTask
import java.util.UUID

class MainActivity : AppCompatActivity() {

    lateinit var menu: Menu
    lateinit var achievmentDrawer: SlidingRootNav
    val REQUEST_PERMISSION_CODE = 100

    var missionTimer = Timer()
    var achievmentTimer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {

        var dateConverter = DateConverter()

        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this, Data.actualTheme)


        // var preferences = getSharedPreferences("date", Context.MODE_PRIVATE)
        //Data.date = preferences.getString(Constants.preferences.DATE_PREFERENCES,"03-02-2018")

        //Realm.deleteRealm(Realm.getDefaultConfiguration())
        if (Data.wishList.isEmpty()) {
            RealmDB.getWishes(this)
        }

        if (Data.cravingsCardList.isEmpty()) {
            RealmDB.loadCards(this)
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

        var achievmentRecyclerView = findViewById<RecyclerView>(R.id.achievment_day_list)
        achievmentRecyclerView.layoutManager = LinearLayoutManager(this)
        var achievmentMoneyRecyclerView = findViewById<RecyclerView>(R.id.achievment_money_list)
        achievmentMoneyRecyclerView.layoutManager = LinearLayoutManager(this)


        if (Data.achievmentList.size == 0) {
            for (i in 1..7) {
                Data.achievmentList.add(Achievment(resources.getDrawable(R.drawable.empty_days), "END", "dasdasdasd",dateConverter.convertDateToTimestamp(Data.date) + i * 2 * Constants.timeConst.oneDay, false,0))
            }
        }

        if (Data.achievmentsMoneyList.size == 0) {
            Data.achievmentsMoneyList.add(Achievment(resources.getDrawable(R.drawable.empty_stars),"1${Data.MoneyDashboard.currency} earned","You earned 1${Data.MoneyDashboard.currency}",0,false,1))
            Data.achievmentsMoneyList.add(Achievment(resources.getDrawable(R.drawable.empty_stars),"3${Data.MoneyDashboard.currency} earned","You earned 3${Data.MoneyDashboard.currency}",0,false,3))
            Data.achievmentsMoneyList.add(Achievment(resources.getDrawable(R.drawable.empty_stars),"5${Data.MoneyDashboard.currency} earned","You earned 5${Data.MoneyDashboard.currency}",0,false,5))
            Data.achievmentsMoneyList.add(Achievment(resources.getDrawable(R.drawable.empty_stars),"10${Data.MoneyDashboard.currency} earned","You earned 10${Data.MoneyDashboard.currency}",0,false,10))
            Data.achievmentsMoneyList.add(Achievment(resources.getDrawable(R.drawable.empty_stars),"20${Data.MoneyDashboard.currency} earned","You earned 20${Data.MoneyDashboard.currency}",0,false,20))
            Data.achievmentsMoneyList.add(Achievment(resources.getDrawable(R.drawable.empty_stars),"50${Data.MoneyDashboard.currency} earned","You earned 50${Data.MoneyDashboard.currency}",0,false,50))
            Data.achievmentsMoneyList.add(Achievment(resources.getDrawable(R.drawable.empty_stars),"100${Data.MoneyDashboard.currency} earned","You earned 100${Data.MoneyDashboard.currency}",0,false,100))
        }




        var achievmentAdapter = AchievmentAdapter(Data.achievmentList,this)
        var moneyAchievmentAdapter = AchievmentAdapter(Data.achievmentsMoneyList,this)
        achievmentRecyclerView.adapter = achievmentAdapter
        achievmentMoneyRecyclerView.adapter = moneyAchievmentAdapter

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

                    this.menu.findItem(R.id.settings).isVisible = true
                    this.menu.findItem(R.id.settings).isEnabled = true
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
                    this.menu.findItem(R.id.settings).isVisible = false
                    this.menu.findItem(R.id.settings).isEnabled = false

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

                    this.menu.findItem(R.id.settings).isVisible = false
                    this.menu.findItem(R.id.settings).isEnabled = false

                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.setDisplayShowHomeEnabled(false)
                    supportActionBar?.title = resources.getString(R.string.title_cravings)
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, CravingFragment(), "cravings").commit()
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
                    var mission = Mission(UUID.randomUUID().toString(), "Mission $i", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(), Constants.timeConst.oneDay), false, false, activities, 0,this)
                    missionList.add(mission)
                } else {
                    if (i == date.days.toInt() + 1) {
                        var mission = Mission(UUID.randomUUID().toString(), "Mission $i", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(), Constants.timeConst.oneDay), false, false, activities, 0,this)
                        missionList.add(mission)
                    } else {
                        var mission = Mission(UUID.randomUUID().toString(), "Mission $i", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(), Constants.timeConst.oneDay), false, false, activities, 0,this)
                        missionList.add(mission)
                    }
                }
            }
            Data.missionList.addAll(missionList)

            for (mission in Data.missionList) {
                RealmDB.addMission(mission)
            }
        }

        missionTimer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                for (i in 1..21) {
                    var date = Date(dateConverter.getCurrentTimestamp() - dateConverter.convertDateToTimestamp(Data.date), Constants.timeConst.twentyOneDays)
                    if (i <= date.days) {
                        var mission = Mission(Data.missionList[i - 1].id, "Mission $i", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date((dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay) + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(), Constants.timeConst.oneDay), false, false, Data.missionList[i - 1].activities!!, Data.missionList[i - 1].done,this@MainActivity)
                        Data.missionList[i - 1].setMission(mission)
                        Data.missionList[i - 1].getDone()
                    } else {
                        if (i == date.days.toInt() + 1) {
                            var mission = Mission(Data.missionList[i - 1].id, "Mission $i", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(), Constants.timeConst.oneDay), false, true, Data.missionList[i - 1].activities!!, Data.missionList[i - 1].done,this@MainActivity)
                            Data.missionList[i - 1].setMission(mission)
                            Data.missionList[i - 1].getDone()
                            //Log.i(Data.missionList[i - 1].name ,  "${Data.missionList[i - 1].observableCompletionDate?.get()?.hours}  ${Data.missionList[i - 1].observableCompletionDate?.get()?.minutes} ${Data.missionList[i - 1].observableCompletionDate?.get()?.seconds}")
                            Log.i("MISSION", Data.missionList[i - 1].completionTime.get())
                        } else {
                            var mission = Mission(Data.missionList[i - 1].id, "Mission $i", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(), Constants.timeConst.oneDay), true, false, Data.missionList[i - 1].activities!!, Data.missionList[i - 1].done,this@MainActivity)
                            Data.missionList[i - 1].setMission(mission)
                            Data.missionList[i - 1].getDone()
                        }
                    }
                }
                //Log.i("Missions","Updated")
            }
        }, 0, 1000)

        achievmentTimer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                for (item in Data.achievmentList) {
                    if (item.endTimestamp <= dateConverter.getCurrentTimestamp()) {
                        item.isComplete = true
                        runOnUiThread {
                            achievmentAdapter.notifyDataSetChanged()
                        }
                    } else {
                        item.isComplete = false
                        runOnUiThread {
                            achievmentAdapter.notifyDataSetChanged()
                        }
                    }
                }
                for (item in Data.achievmentsMoneyList) {
                    if (item.price <= Data.MoneyDashboard.moneySaved) {
                        item.isComplete = true
                        runOnUiThread {
                            moneyAchievmentAdapter.notifyDataSetChanged()
                        }
                    } else {
                        item.isComplete = false
                        runOnUiThread {
                            moneyAchievmentAdapter.notifyDataSetChanged()
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
                    var intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                } else {

                }

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onRestart() {

        if (Data.themeChanged) {
            Data.themeChanged = false
            ThemeManager.setTheme(this, Data.actualTheme)
            this.recreate()
        }
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

    override fun onDestroy() {
        this.missionTimer.cancel()
        this.achievmentTimer.cancel()
        super.onDestroy()
    }


}
