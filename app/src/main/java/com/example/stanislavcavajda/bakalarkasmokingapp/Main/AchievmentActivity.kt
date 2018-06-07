package com.example.stanislavcavajda.bakalarkasmokingapp.Main

import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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

        var position = intent.getIntExtra(Constants.extras.EXTRA_ITEM_ID,0)

        var achievment = Data.achievmentList[position]

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

            val intent = Intent(android.content.Intent.ACTION_SEND)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file))
            intent.type = "image/png"
            startActivity(Intent.createChooser(intent, "Share image via"))
        }
    }
}
