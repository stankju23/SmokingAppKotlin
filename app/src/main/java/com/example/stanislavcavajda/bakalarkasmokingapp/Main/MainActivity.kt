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
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager.WishesActivity
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.BottomNavigationViewHelper
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.DateConverter
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.JSONParser
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.Journal.JournalFragment
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
import java.util.Timer
import java.util.TimerTask
import java.util.UUID

class MainActivity : AppCompatActivity() {

    lateinit var menu: Menu
    lateinit var achievmentDrawer: SlidingRootNav
    val REQUEST_PERMISSION_CODE = 100

    var missionTimer = Timer()
    var achievmentTimer = Timer()
    var journalTimer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {

        var dateConverter = DateConverter()

        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this, Data.actualTheme)

        Realm.init(this)
       // var config = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()

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

        if (Data.journalCardSList.isEmpty()) {
            RealmDB.getJournalCard(this)
        }

        if (intent.getBooleanExtra("wishes",false)) {
            var intent = Intent(this,WishesActivity::class.java)
            startActivity(intent)
        }


        var binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        var slidingView = SlidingRootNavBuilder(this)
            .withDragDistance(220)
            .withMenuLayout(R.layout.achievments_drawer_layout)
            .withGravity(SlideGravity.LEFT)
            .withRootViewScale(1f)
            .withRootViewElevation(5)
        achievmentDrawer = slidingView.inject()

        var achievmentRecyclerView = findViewById<RecyclerView>(R.id.achievment_day_list)
        achievmentRecyclerView.layoutManager = LinearLayoutManager(this)
        var achievmentMoneyRecyclerView = findViewById<RecyclerView>(R.id.achievment_money_list)
        achievmentMoneyRecyclerView.layoutManager = LinearLayoutManager(this)


        if (Data.achievmentList.size == 0) {
            Data.achievmentList.add(Achievment(resources.getDrawable(R.drawable.achiemvent_one_day), resources.getString(R.string.achievments_noSmoked_one_title), resources.getString(R.string.achievments_noSmoked_one_desc),dateConverter.convertDateToTimestamp(Data.date) + Constants.timeConst.oneDay, false))
            Data.achievmentList.add(Achievment(resources.getDrawable(R.drawable.achievment_three_days), resources.getString(R.string.achievments_noSmoked_three_title), resources.getString(R.string.achievments_noSmoked_three_desc),dateConverter.convertDateToTimestamp(Data.date) + 3 * Constants.timeConst.oneDay, false))
            Data.achievmentList.add(Achievment(resources.getDrawable(R.drawable.achievment_seven_days), resources.getString(R.string.achievments_noSmoked_seven_title), resources.getString(R.string.achievments_noSmoked_seven_desc),dateConverter.convertDateToTimestamp(Data.date) + 7 * Constants.timeConst.oneDay, false))
            Data.achievmentList.add(Achievment(resources.getDrawable(R.drawable.achievment_twelve_days), resources.getString(R.string.achievments_noSmoked_twelve_title), resources.getString(R.string.achievments_noSmoked_twelve_desc),dateConverter.convertDateToTimestamp(Data.date) + 12 * Constants.timeConst.oneDay, false))
            Data.achievmentList.add(Achievment(resources.getDrawable(R.drawable.achievment_twentyone_days), resources.getString(R.string.achievments_noSmoked_twentyone_title), resources.getString(R.string.achievments_noSmoked_twentyone_desc),dateConverter.convertDateToTimestamp(Data.date) + 21 * Constants.timeConst.oneDay, false))

        }

        if (Data.missionsAchievments.size == 0) {
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_one),"1${Data.MoneyDashboard.currency} earned","You earned 1${Data.MoneyDashboard.currency}",0,false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_two),"3${Data.MoneyDashboard.currency} earned","You earned 3${Data.MoneyDashboard.currency}",0,false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_three),"5${Data.MoneyDashboard.currency} earned","You earned 5${Data.MoneyDashboard.currency}",0,false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_four),"10${Data.MoneyDashboard.currency} earned","You earned 10${Data.MoneyDashboard.currency}",0,false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_five),"20${Data.MoneyDashboard.currency} earned","You earned 20${Data.MoneyDashboard.currency}",0,false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_six),"50${Data.MoneyDashboard.currency} earned","You earned 50${Data.MoneyDashboard.currency}",0,false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_seven),"100${Data.MoneyDashboard.currency} earned","You earned 100${Data.MoneyDashboard.currency}",0,false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_eight),"1${Data.MoneyDashboard.currency} earned","You earned 1${Data.MoneyDashboard.currency}",0,false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_nine),"3${Data.MoneyDashboard.currency} earned","You earned 3${Data.MoneyDashboard.currency}",0,false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_ten),"5${Data.MoneyDashboard.currency} earned","You earned 5${Data.MoneyDashboard.currency}",0,false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_eleven),"10${Data.MoneyDashboard.currency} earned","You earned 10${Data.MoneyDashboard.currency}",0,false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_twelve),"20${Data.MoneyDashboard.currency} earned","You earned 20${Data.MoneyDashboard.currency}",0,false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_thirteen),"50${Data.MoneyDashboard.currency} earned","You earned 50${Data.MoneyDashboard.currency}",0,false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_fourteen),"100${Data.MoneyDashboard.currency} earned","You earned 100${Data.MoneyDashboard.currency}",0,false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_fifteen),"1${Data.MoneyDashboard.currency} earned","You earned 1${Data.MoneyDashboard.currency}",0,false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_sixteen),"3${Data.MoneyDashboard.currency} earned","You earned 3${Data.MoneyDashboard.currency}",0,false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_seventeen),"5${Data.MoneyDashboard.currency} earned","You earned 5${Data.MoneyDashboard.currency}",0,false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_eighteen),"10${Data.MoneyDashboard.currency} earned","You earned 10${Data.MoneyDashboard.currency}",0,false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_nineteen),"20${Data.MoneyDashboard.currency} earned","You earned 20${Data.MoneyDashboard.currency}",0,false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_twenty),"50${Data.MoneyDashboard.currency} earned","You earned 50${Data.MoneyDashboard.currency}",0,false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_twentyone),"100${Data.MoneyDashboard.currency} earned","You earned 100${Data.MoneyDashboard.currency}",0,false))

        }




        var achievmentAdapter = AchievmentAdapter(Data.achievmentList,this)
        var moneyAchievmentAdapter = AchievmentAdapter(Data.missionsAchievments,this)
        achievmentRecyclerView.adapter = achievmentAdapter
        achievmentMoneyRecyclerView.adapter = moneyAchievmentAdapter

        var toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.achievment_icon)
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
                    supportActionBar?.setHomeAsUpIndicator(R.drawable.achievment_icon)
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

                    this.menu.findItem(R.id.settings).isVisible = false
                    this.menu.findItem(R.id.settings).isEnabled = false
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.setDisplayShowHomeEnabled(false)
                    supportActionBar?.title = resources.getString(R.string.title_journal)
                    fragmentManager.beginTransaction().replace(R.id.fragment_container,JournalFragment(),"journal").commit()

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

            for (i in 0..20) {
                var activities = ArrayList<Activity>()
                for (j in 1..5) {
                    var activity = Activity(false, Data.objectives[i * 5 + j - 1], UUID.randomUUID().toString())
                    activities.add(activity)
                }
                var date = Date(dateConverter.getCurrentTimestamp() - dateConverter.convertDateToTimestamp(Data.date), Constants.timeConst.twentyOneDays)
                if (i <= date.days) {
                    var mission = Mission(UUID.randomUUID().toString(), "", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(), Constants.timeConst.oneDay), false, false, activities, 0,this)
                    missionList.add(mission)
                } else {
                    if (i == date.days.toInt() + 1) {
                        var mission = Mission(UUID.randomUUID().toString(), "", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(), Constants.timeConst.oneDay), false, false, activities, 0,this)
                        missionList.add(mission)
                    } else {
                        var mission = Mission(UUID.randomUUID().toString(), "", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(), Constants.timeConst.oneDay), false, false, activities, 0,this)
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
                        var mission = Mission(Data.missionList[i - 1].id, "${resources.getString(R.string.mission)} $i", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date((dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay) + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(), Constants.timeConst.oneDay), false, false, Data.missionList[i - 1].activities!!, Data.missionList[i - 1].done,this@MainActivity)
                        Data.missionList[i - 1].setMission(mission)
                        Data.missionList[i - 1].getDone()
                    } else {
                        if (i == date.days.toInt() + 1) {
                            var mission = Mission(Data.missionList[i - 1].id, "${resources.getString(R.string.mission)} $i", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(), Constants.timeConst.oneDay), false, true, Data.missionList[i - 1].activities!!, Data.missionList[i - 1].done,this@MainActivity)
                            Data.missionList[i - 1].setMission(mission)
                            Data.missionList[i - 1].getDone()
                            //Log.i(Data.missionList[i - 1].name ,  "${Data.missionList[i - 1].observableCompletionDate?.get()?.hours}  ${Data.missionList[i - 1].observableCompletionDate?.get()?.minutes} ${Data.missionList[i - 1].observableCompletionDate?.get()?.seconds}")
                            Log.i("MISSION", Data.missionList[i - 1].completionTime.get())
                        } else {
                            var mission = Mission(Data.missionList[i - 1].id, "${resources.getString(R.string.mission)} $i", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(), Constants.timeConst.oneDay), true, false, Data.missionList[i - 1].activities!!, Data.missionList[i - 1].done,this@MainActivity)
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

                var numberOfCompleteMissions = 0
                for (item in Data.missionList) {
                    if(item.done == 5) {
                        numberOfCompleteMissions++
                    }
                }

                var index = 0
                for (item in Data.missionsAchievments) {
                    if (index < numberOfCompleteMissions) {
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
                    index++
                }
            }
        }, 0, 1000)

        journalTimer.scheduleAtFixedRate(object :TimerTask() {
            override fun run() {
                var currentTimestamp = dateConverter.getCurrentTimestamp()
                for (item in Data.journalCardSList) {
                    item.updateJournal(currentTimestamp)
                }
            }
        },0,1000)
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

    override fun onResume() {
        super.onResume()
    }
    override fun onDestroy() {
        this.missionTimer.cancel()
        this.achievmentTimer.cancel()
        this.journalTimer.cancel()
        super.onDestroy()
    }


}
