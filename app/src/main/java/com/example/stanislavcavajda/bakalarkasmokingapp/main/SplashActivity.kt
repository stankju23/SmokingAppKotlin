package com.example.stanislavcavajda.bakalarkasmokingapp.main

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.DataManager
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.ui.theme.SmokingAppTheme
import com.example.stanislavcavajda.bakalarkasmokingapp.walkthrough.Walkthrough
import com.example.stanislavcavajda.bakalarkasmokingapp.walkthrough.WalkthroughFirst
import io.realm.Realm

class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SmokingAppTheme() {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = walkthrough_1) {
                    composable(walkthrough_1) {
                        WalkthroughFirst()
                    }
                    composable(walkthrough_2) {}
                    composable(walkthrough_3) {}
                    composable(walkthrough_4) {}
                    composable(walkthrough_5) {}
                }
            }

        }
//        Realm.init(this)

//        ThemeManager.setTheme(this,Data.actualTheme)
//
//
//
//        var preferences = getSharedPreferences("walkthrough", Context.MODE_PRIVATE)
//        Data.firstTime = preferences.getBoolean(Constants.preferences.FIRST_TIME,false)
//
//        if (!Data.firstTime) {
//
//            var mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            var name = "Notification Channel"
//            var desc = "Toto je novy notifikacny channel"
//
//            var importance = NotificationManager.IMPORTANCE_DEFAULT
//            if (android.os.Build.VERSION.SDK_INT >=  android.os.Build.VERSION_CODES.O) {
//                var mChannel = NotificationChannel(Data.notificationChannel,name,importance)
//                mChannel.description = desc
//                mChannel.enableLights(true)
//                mChannel.lightColor = Color.RED
//                mChannel.enableVibration(true)
//                mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
//                mNotificationManager.createNotificationChannel(mChannel)
//            }
//
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//                var walkthrough = Intent(this, Walkthrough::class.java)
//                startActivity(walkthrough)
//            } else {
//                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),100)
//                recreate()
//            }
//
//
//        } else {
//            DataManager.loadMainData(this)
//
//            var mainActivity = Intent(this,MainActivity::class.java)
//            startActivity(mainActivity)
//        }
    }

    companion object Walkthrough {
        const val walkthrough_1 = "walkthrough_1"
        const val walkthrough_2 = "walkthrough_2"
        const val walkthrough_3 = "walkthrough_3"
        const val walkthrough_4 = "walkthrough_4"
        const val walkthrough_5 = "walkthrough_5"
    }
}

@Preview
@Composable
fun previewSplashScreen() {

}
