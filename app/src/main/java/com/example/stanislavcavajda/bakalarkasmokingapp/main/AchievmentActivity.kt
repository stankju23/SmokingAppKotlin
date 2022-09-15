package com.example.stanislavcavajda.bakalarkasmokingapp.main

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.core.content.FileProvider
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.BuildConfig
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityAchievmentBinding
import java.io.File
import java.io.FileOutputStream

class AchievmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this,Data.actualTheme)


        var binding:ActivityAchievmentBinding = ActivityAchievmentBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var toolbar = findViewById<Toolbar>(R.id.achievment_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)


        var position = intent.getIntExtra(Constants.extras.EXTRA_ITEM_ID,0)
        var achievment:Achievment
        if (intent.getBooleanExtra("mission",false) == false) {
            achievment = Data.achievmentList[position]
        } else {
            achievment = Data.missionsAchievments[position]
        }

        binding.achievment = achievment

        binding.shareBtn.setOnClickListener {
            binding.shareScreen.isDrawingCacheEnabled = true
            binding.shareScreen.buildDrawingCache()
            var screenBmp = binding.shareScreen.getDrawingCache()

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
                Log.i("Share", e.message ?: "")
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
