package com.example.stanislavcavajda.bakalarkasmokingapp.Main

import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.BuildConfig
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityAchievmentBinding
import kotlinx.android.synthetic.main.activity_achievment.*
import java.io.File
import java.io.FileOutputStream

class AchievmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this,Data.actualTheme)


        var binding:ActivityAchievmentBinding = DataBindingUtil.setContentView(this,R.layout.activity_achievment)


        var toolbar = findViewById<Toolbar>(R.id.achievment_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)


        var position = intent.getIntExtra(Constants.extras.EXTRA_ITEM_ID,0)
        var achievment:Achievment
        if (intent.getBooleanExtra("money",false) == false) {
            achievment = Data.achievmentList[position]
        } else {
            achievment = Data.achievmentsMoneyList[position]
        }

        binding.achievment = achievment

        share_btn.setOnClickListener {
            share_screen.isDrawingCacheEnabled = true
            share_screen.buildDrawingCache()
            var screenBmp = share_screen.getDrawingCache()

            val file = File(this.externalCacheDir,"logicchip.png")
            val fOut = FileOutputStream(file)
            screenBmp.compress(Bitmap.CompressFormat.PNG,100,fOut)
            fOut.flush()
            fOut.close()
            file.setReadable(true,false)

            try {
                val photoURI = FileProvider.getUriForFile(this,
                    BuildConfig.APPLICATION_ID + ".provider",
                    file)
                val intent = Intent(android.content.Intent.ACTION_SEND)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra(Intent.EXTRA_STREAM, photoURI)
                intent.type = "image/png"
                startActivity(Intent.createChooser(intent, "Share image via"))
            } catch (e:Exception) {
                Log.i("Share", e.message)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
