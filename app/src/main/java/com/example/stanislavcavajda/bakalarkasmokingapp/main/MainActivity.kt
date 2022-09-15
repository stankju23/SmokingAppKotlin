package com.example.stanislavcavajda.bakalarkasmokingapp.main

//import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.DateConverter
//import com.example.stanislavcavajda.bakalarkasmokingapp.Cravings.CravingFragment
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.stanislavcavajda.bakalarkasmokingapp.cravings.CravingFragment
import com.example.stanislavcavajda.bakalarkasmokingapp.dashboard.DashboardFragment
import com.example.stanislavcavajda.bakalarkasmokingapp.dashboard.healthlist.HealthProgressListViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.dashboard.mainprogress.MainProgressViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.dashboard.moneysaved.MoneySavedViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.dashboard.wishmanager.Wish
import com.example.stanislavcavajda.bakalarkasmokingapp.dashboard.wishmanager.WishListViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.dashboard.wishmanager.WishesActivity
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.BottomNavigationViewHelper
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.DataManager
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.DateConverter
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.JSONParser
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.journal.JournalFragment
import com.example.stanislavcavajda.bakalarkasmokingapp.journal.JournalStoryActivity
import com.example.stanislavcavajda.bakalarkasmokingapp.missions.Activity
import com.example.stanislavcavajda.bakalarkasmokingapp.missions.Mission
import com.example.stanislavcavajda.bakalarkasmokingapp.missions.MissionsFragment
import com.example.stanislavcavajda.bakalarkasmokingapp.model.Date
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.realmdatabase.RealmDB
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityMainBinding
import io.realm.Realm
import java.text.DecimalFormat
import java.util.Timer
import java.util.TimerTask
import java.util.UUID

class MainActivity : AppCompatActivity() {

    lateinit var menu: Menu

    val REQUEST_PERMISSION_CODE = 100

    var missionTimer = Timer()
    var achievmentTimer = Timer()
    var journalTimer = Timer()
    var dashboardTimer = Timer()
    var timer = Timer()

    lateinit var dateConverter: DateConverter

    var wordList = ArrayList<String>()

    lateinit var date: Date

    var progress = 0f

    var decimalFormat = DecimalFormat("###.##")

    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        dateConverter = DateConverter()

        super.onCreate(savedInstanceState)

        try {
            Realm.init(this)
        } catch (e: Exception) {
            Log.e("Realm", e.message ?: "")
        }

        date = Date(dateConverter.getCurrentTimestamp() - dateConverter.convertDateToTimestamp(Data.date), Constants.timeConst.twentyOneDays)

        if (Data.healthProgressViewList.isEmpty()) {
            prepareHealthProgressList()
        }

        if (intent.getBooleanExtra("wishes", false)) {
            DataManager.loadMainData(this)
            var intent = Intent(this, WishesActivity::class.java)
            startActivity(intent)
        }

        ThemeManager.setTheme(this, Data.actualTheme)

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

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

//        var slidingView = SlidingRootNavBuilder(this)
//            .withDragDistance(220)
//            .withMenuLayout(R.layout.achievments_drawer_layout)
//            .withGravity(SlideGravity.LEFT)
//            .withRootViewScale(1f)
//            .withRootViewElevation(5)
//        achievmentDrawer = slidingView.inject()

        var achievmentRecyclerView = findViewById<RecyclerView>(R.id.achievment_day_list)
        achievmentRecyclerView.layoutManager =
            LinearLayoutManager(this)
        var achievmentMoneyRecyclerView = findViewById<RecyclerView>(R.id.achievment_money_list)
        achievmentMoneyRecyclerView.layoutManager =
            LinearLayoutManager(this)


        if (Data.achievmentList.size == 0) {
            Data.achievmentList.add(Achievment(resources.getDrawable(R.drawable.achiemvent_one_day), resources.getString(R.string.achievments_noSmoked_one_title), resources.getString(R.string.achievments_noSmoked_one_desc), dateConverter.convertDateToTimestamp(Data.date) + Constants.timeConst.oneDay, false))
            Data.achievmentList.add(Achievment(resources.getDrawable(R.drawable.achievment_three_days), resources.getString(R.string.achievments_noSmoked_three_title), resources.getString(R.string.achievments_noSmoked_three_desc), dateConverter.convertDateToTimestamp(Data.date) + 3 * Constants.timeConst.oneDay, false))
            Data.achievmentList.add(Achievment(resources.getDrawable(R.drawable.achievment_seven_days), resources.getString(R.string.achievments_noSmoked_seven_title), resources.getString(R.string.achievments_noSmoked_seven_desc), dateConverter.convertDateToTimestamp(Data.date) + 7 * Constants.timeConst.oneDay, false))
            Data.achievmentList.add(Achievment(resources.getDrawable(R.drawable.achievment_twelve_days), resources.getString(R.string.achievments_noSmoked_twelve_title), resources.getString(R.string.achievments_noSmoked_twelve_desc), dateConverter.convertDateToTimestamp(Data.date) + 12 * Constants.timeConst.oneDay, false))
            Data.achievmentList.add(Achievment(resources.getDrawable(R.drawable.achievment_twentyone_days), resources.getString(R.string.achievments_noSmoked_twentyone_title), resources.getString(R.string.achievments_noSmoked_twentyone_desc), dateConverter.convertDateToTimestamp(Data.date) + 21 * Constants.timeConst.oneDay, false))
        }

        if (Data.missionsAchievments.size == 0) {
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_one), resources.getString(R.string.congratulation), "${resources.getString(R.string.achievment_complete)} 1 ${resources.getString(R.string.missions)}", 0, false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_two), resources.getString(R.string.congratulation), "${resources.getString(R.string.achievment_complete)} 2 ${resources.getString(R.string.missions)}", 0, false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_three), resources.getString(R.string.congratulation), "${resources.getString(R.string.achievment_complete)} 3 ${resources.getString(R.string.missions)}", 0, false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_four), resources.getString(R.string.congratulation), "${resources.getString(R.string.achievment_complete)} 4 ${resources.getString(R.string.missions)}", 0, false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_five), resources.getString(R.string.congratulation), "${resources.getString(R.string.achievment_complete)} 5 ${resources.getString(R.string.missions)}", 0, false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_six), resources.getString(R.string.congratulation), "${resources.getString(R.string.achievment_complete)} 6 ${resources.getString(R.string.missions)}", 0, false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_seven), resources.getString(R.string.congratulation), "${resources.getString(R.string.achievment_complete)} 7 ${resources.getString(R.string.missions)}", 0, false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_eight), resources.getString(R.string.congratulation), "${resources.getString(R.string.achievment_complete)} 8 ${resources.getString(R.string.missions)}", 0, false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_nine), resources.getString(R.string.congratulation), "${resources.getString(R.string.achievment_complete)} 9 ${resources.getString(R.string.missions)}", 0, false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_ten), resources.getString(R.string.congratulation), "${resources.getString(R.string.achievment_complete)} 10 ${resources.getString(R.string.missions)}", 0, false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_eleven), resources.getString(R.string.congratulation), "${resources.getString(R.string.achievment_complete)} 11 ${resources.getString(R.string.missions)}", 0, false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_twelve), resources.getString(R.string.congratulation), "${resources.getString(R.string.achievment_complete)} 12 ${resources.getString(R.string.missions)}", 0, false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_thirteen), resources.getString(R.string.congratulation), "${resources.getString(R.string.achievment_complete)} 13 ${resources.getString(R.string.missions)}", 0, false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_fourteen), resources.getString(R.string.congratulation), "${resources.getString(R.string.achievment_complete)} 14 ${resources.getString(R.string.missions)}", 0, false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_fifteen), resources.getString(R.string.congratulation), "${resources.getString(R.string.achievment_complete)} 15 ${resources.getString(R.string.missions)}", 0, false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_sixteen), resources.getString(R.string.congratulation), "${resources.getString(R.string.achievment_complete)} 16 ${resources.getString(R.string.missions)}", 0, false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_seventeen), resources.getString(R.string.congratulation), "${resources.getString(R.string.achievment_complete)} 17 ${resources.getString(R.string.missions)}", 0, false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_eighteen), resources.getString(R.string.congratulation), "${resources.getString(R.string.achievment_complete)} 18 ${resources.getString(R.string.missions)}", 0, false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_nineteen), resources.getString(R.string.congratulation), "${resources.getString(R.string.achievment_complete)} 19 ${resources.getString(R.string.missions)}", 0, false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_twenty), resources.getString(R.string.congratulation), "${resources.getString(R.string.achievment_complete)} 20 ${resources.getString(R.string.missions)}", 0, false))
            Data.missionsAchievments.add(Achievment(resources.getDrawable(R.drawable.achievment_mission_twentyone), resources.getString(R.string.congratulation), "${resources.getString(R.string.achievment_complete)} 21 ${resources.getString(R.string.missions)}", 0, false))
        }

        var achievmentAdapter = AchievmentAdapter(Data.achievmentList, this)
        var moneyAchievmentAdapter = AchievmentAdapter(Data.missionsAchievments, this)
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

        binding?.navigation?.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {

            when (it.itemId) {

                R.id.navigation_dasboard -> {
//                    this.achievmentDrawer.isMenuLocked = false

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
//                    this.achievmentDrawer.isMenuLocked = true
//                    if (this.achievmentDrawer.isMenuOpened) {
//                        this.achievmentDrawer.closeMenu(true)
//                    }
                    this.menu.findItem(R.id.settings).isVisible = false
                    this.menu.findItem(R.id.settings).isEnabled = false

                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.setDisplayShowHomeEnabled(false)
                    supportActionBar?.title = resources.getString(R.string.title_mission)

                    fragmentManager.beginTransaction().replace(R.id.fragment_container, MissionsFragment(), "missions").commit()
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_cravings -> {

//                    this.achievmentDrawer.isMenuLocked = true
//                    if (this.achievmentDrawer.isMenuOpened) {
//                        this.achievmentDrawer.closeMenu(true)
//                    }

                    this.menu.findItem(R.id.settings).isVisible = false
                    this.menu.findItem(R.id.settings).isEnabled = false

                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.setDisplayShowHomeEnabled(false)
                    supportActionBar?.title = resources.getString(R.string.title_cravings)
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, CravingFragment(), "cravings").commit()
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_infoarea -> {

//                    this.achievmentDrawer.isMenuLocked = true
//
//                    if (this.achievmentDrawer.isMenuOpened) {
//                        this.achievmentDrawer.closeMenu(true)
//                    }

                    this.menu.findItem(R.id.settings).setIcon(R.drawable.book)
                    this.menu.findItem(R.id.settings).isVisible = true
                    this.menu.findItem(R.id.settings).isEnabled = true

                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.setDisplayShowHomeEnabled(false)
                    supportActionBar?.title = resources.getString(R.string.title_journal)
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, JournalFragment(), "journal").commit()

                    return@OnNavigationItemSelectedListener true
                }

            }
            true
        })

        binding?.navigation?.let {
            BottomNavigationViewHelper.disableShiftMode(it)
        }



        if (Data.missionList.isEmpty()) {
            RealmDB.getMissions(this)
        }


        if (Data.missionList.isEmpty()) {

            Data.objectives = JSONParser.parseObjectives(JSONParser.loadJsonFromAssets("Objectives", this), this)

            var missionList = ArrayList<Mission>()

            for (i in 0..20) {
                var activities = ArrayList<Activity>()
                for (j in 1..Data.numberOfObjectivesInCravings) {
                    var activity = Activity(false, Data.objectives[i * Data.numberOfObjectivesInCravings + (j - 1)], UUID.randomUUID().toString())
                    activities.add(activity)
                }
                var date = Date(dateConverter.getCurrentTimestamp() - dateConverter.convertDateToTimestamp(Data.date), Constants.timeConst.twentyOneDays)
                if (i <= date.days) {
                    var mission = Mission(UUID.randomUUID().toString(), "", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(), Constants.timeConst.oneDay), false, false, activities, 0, this)
                    missionList.add(mission)
                } else {
                    if (i == date.days.toInt() + 1) {
                        var mission = Mission(UUID.randomUUID().toString(), "", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(), Constants.timeConst.oneDay), false, false, activities, 0, this)
                        missionList.add(mission)
                    } else {
                        var mission = Mission(UUID.randomUUID().toString(), "", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(), Constants.timeConst.oneDay), false, false, activities, 0, this)
                        missionList.add(mission)
                    }
                }
            }
            Data.missionList.addAll(missionList)

            for (mission in Data.missionList) {
                RealmDB.addMission(mission)
            }
        }

        var currentTimestamp = dateConverter.getCurrentTimestamp()
        var dateTimestamp = dateConverter.convertDateToTimestamp(Data.date)
        var result = decimalFormat.format(actualSaved(currentTimestamp - dateTimestamp, Data.MoneyDashboard.cigarretesPerDay, Data.MoneyDashboard.packagePrice, Data.MoneyDashboard.cigarretesInPackage))

        result = result.replace(",", ".")


        Data.MoneyDashboard.moneySaved = result.toFloat()
        updateWishList()

        var healthProgress = HealthProgressListViewModel(Data.healthProgressViewList, this)
        var mainProgress = MainProgressViewModel(date, this, Data.timeList)
        var wishesManager = WishListViewModel(Data.wishList, this)
        var moneySaved = MoneySavedViewModel(Data.MoneyDashboard.moneySaved, Data.MoneyDashboard.moneySpend, Data.MoneyDashboard.currency)

        if (Data.dashboardList.size == 0) {
            Data.dashboardList.add(mainProgress as Object)
            Data.dashboardList.add(healthProgress as Object)
            Data.dashboardList.add(wishesManager as Object)
            Data.dashboardList.add(moneySaved as Object)
        }

        wordList = ArrayList<String>()
        wordList.add(resources.getString(R.string.days))
        wordList.add(resources.getString(R.string.hours))
        wordList.add(resources.getString(R.string.minutes))
        wordList.add(resources.getString(R.string.seconds))


        dashboardTimer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                if (this != null) {
                    currentTimestamp = dateConverter.getCurrentTimestamp()
                    dateTimestamp = dateConverter.convertDateToTimestamp(Data.date)
                    date = Date(currentTimestamp - dateTimestamp, Constants.timeConst.twentyOneDays)

                    progress = (dateConverter.getCurrentTimestamp() - dateConverter.convertDateToTimestamp(Data.date)).toFloat() / (Constants.timeConst.twentyOneDays).toFloat() * 100f

                    (Data.dashboardList.get(Constants.viewTypes.MAIN_PROGRESS_VIEW_TYPE) as MainProgressViewModel).setProgress(date)

                    Data.timeList.clear()
                    Data.timeList.addAll(dateConverter.updateMainProgressDetail(currentTimestamp - dateTimestamp, wordList))

                    (Data.dashboardList.get(Constants.viewTypes.MAIN_PROGRESS_VIEW_TYPE) as MainProgressViewModel).updateDetail(Data.timeList)

                    updateHealthProgress(currentTimestamp, dateTimestamp)


                    (Data.dashboardList.get(Constants.viewTypes.HEALTH_PROGRESS_VIEW_TYPE) as HealthProgressListViewModel).updateAll()
                }
            }
        }, 0, 1000)

        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                try {
                    result = decimalFormat.format(actualSaved(currentTimestamp - dateTimestamp, Data.MoneyDashboard.cigarretesPerDay, Data.MoneyDashboard.packagePrice, Data.MoneyDashboard.cigarretesInPackage))
                    result = result.replace(",", ".")
                    Data.MoneyDashboard.moneySaved = result.toFloat()

                    (Data.dashboardList.get(Constants.viewTypes.MONEY_SAVED_VIEW_TYPE) as MoneySavedViewModel).updateMoney(
                        Data.MoneyDashboard.moneySaved, 0.0f, Data.MoneyDashboard.currency)

                    updateWishList()

                    val list = Data.wishList.sortedWith(compareBy(Wish::price))
                    Data.wishList = ArrayList(list)

                    (Data.dashboardList.get(Constants.viewTypes.WISHES_MANAGER_VIEW_TYPE) as WishListViewModel).updateWishList(Data.wishList)

                    Data.MoneyDashboard.actualMoneyState = Data.MoneyDashboard.moneySaved - Data.MoneyDashboard.moneySpend
                } catch (e: Exception) {

                }
            }
        }, 0, 1000)


        missionTimer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                for (i in 1..21) {
                    var date = Date(dateConverter.getCurrentTimestamp() - dateConverter.convertDateToTimestamp(Data.date), Constants.timeConst.twentyOneDays)
                    if (i <= date.days) {
                        var mission = Mission(Data.missionList[i - 1].id, "${resources.getString(R.string.mission)} $i", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date((dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay) + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(), Constants.timeConst.oneDay), false, false, Data.missionList[i - 1].activities!!, Data.missionList[i - 1].done, this@MainActivity)
                        Data.missionList[i - 1].setMission(mission)
                        Data.missionList[i - 1].getDone()
                    } else {
                        if (i == date.days.toInt() + 1) {
                            var mission = Mission(Data.missionList[i - 1].id, "${resources.getString(R.string.mission)} $i", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(), Constants.timeConst.oneDay), false, true, Data.missionList[i - 1].activities!!, Data.missionList[i - 1].done, this@MainActivity)
                            Data.missionList[i - 1].setMission(mission)
                            Data.missionList[i - 1].getDone()
//                            Log.i("MISSION", Data.missionList[i - 1].completionTime.get())
                        } else {
                            var mission = Mission(Data.missionList[i - 1].id, "${resources.getString(R.string.mission)} $i", dateConverter.getDate(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay), Date(dateConverter.convertDateToTimestamp(Data.date) + (i - 1) * Constants.timeConst.oneDay + Constants.timeConst.oneDay - dateConverter.getCurrentTimestamp(), Constants.timeConst.oneDay), true, false, Data.missionList[i - 1].activities!!, Data.missionList[i - 1].done, this@MainActivity)
                            Data.missionList[i - 1].setMission(mission)
                            Data.missionList[i - 1].getDone()
                        }
                    }
                }
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
                    if (item.done == Data.numberOfObjectivesInCravings) {
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

        journalTimer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                var currentTimestamp = dateConverter.getCurrentTimestamp()
                for (item in Data.journalCardSList) {
                    item.updateJournal(currentTimestamp)
                }
            }
        }, 0, 1000)
    }

    override fun onBackPressed() {
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        this.menu = menu!!
        menuInflater.inflate(R.menu.action_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
//                if (achievmentDrawer.isMenuOpened) {
//                    achievmentDrawer.closeMenu(true)
//                } else {
//                    achievmentDrawer.openMenu(true)
//                }
                return true
            }
            R.id.settings -> {
                if (binding?.navigation?.selectedItemId == R.id.navigation_dasboard) {
                    var intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                } else {
                    if (binding?.navigation?.selectedItemId == R.id.navigation_infoarea) {
                        var storyItems = 0
                        for (item in Data.journalCardSList) {
                            if (item.title.get()?.length != 0 && item.desc.get()?.length != 0) {
                                storyItems++
                            }
                        }

                        if (storyItems != 0) {
                            var intent = Intent(this, JournalStoryActivity::class.java);
                            this.startActivity(intent)
                        } else {
                            Toast.makeText(this, "You have not story elements :/", Toast.LENGTH_SHORT).show()
                        }
                    }
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
        this.dashboardTimer.cancel()
        this.timer.cancel()
        super.onDestroy()
    }

    fun actualSaved(actualTimestamp: Long, cigarretesPerDay: Int, packagePrice: Double, inPackage: Int): Float {
        var result = 0.0f
        var day = 86400
        result = (((packagePrice.toFloat() / inPackage.toFloat()) * cigarretesPerDay) / day.toFloat()) * actualTimestamp
        return result
    }

    fun updateWishList() {
        for (i in Data.wishList) {
            i.setWish(i.title.get()!!, i.desc.get()!!, i.price, i.image!!)
        }
    }

    fun prepareHealthProgressList() {

        when (resources.configuration.locale.country) {
            "US" -> Data.healthProgressViewList.addAll(JSONParser.parseHealthData(JSONParser.loadJsonFromAssets("HealthDataEN", this), this))
            "SK" -> Data.healthProgressViewList.addAll(JSONParser.parseHealthData(JSONParser.loadJsonFromAssets("HealthDataSK", this), this))
            else -> Data.healthProgressViewList.addAll(JSONParser.parseHealthData(JSONParser.loadJsonFromAssets("HealthDataEN", this), this))
        }
    }

    fun updateHealthProgress(currentTimestamp: Long, dateTimestamp: Long) {
        var index = 0
        for (item in Data.healthProgressTimes) {
            date = Date(dateTimestamp + item - currentTimestamp, item)
            Data.healthProgressViewList[index].date.set(date)
            index++
        }
    }
}
