package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.HealthList

import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.content.FileProvider
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.BuildConfig
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityHealthProgressDetailBinding
import com.github.bluzwong.swipeback.SwipeBackActivityHelper
import kotlinx.android.synthetic.main.activity_health_progress_detail.*
import java.io.File
import java.io.FileOutputStream

class HealthProgressDetailActivity : AppCompatActivity() {

    var helper = SwipeBackActivityHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this,Data.actualTheme)

        var binding: ActivityHealthProgressDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_health_progress_detail)
        var itemId = intent.getIntExtra(Constants.extras.EXTRA_ITEM_ID,0)
        var healthProgres = Data.healthProgressViewList[itemId]

        setSupportActionBar(health_progress_detail_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)
        binding.viewModel = healthProgres

        share_btn.setOnClickListener {
            health_detail_layout.isDrawingCacheEnabled = true
            health_detail_layout.buildDrawingCache()
            var screenBmp = health_detail_layout.getDrawingCache()

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
                val intent = Intent(Intent.ACTION_SEND)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                intent.putExtra(Intent.EXTRA_STREAM, photoURI)
                intent.type = "image/png"
                startActivity(Intent.createChooser(intent, "Share image via"))
            } catch (e:Exception) {
                Log.i("Share", e.message)
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        this.finish()
        super.onBackPressed()
    }
}
