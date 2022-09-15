package com.example.stanislavcavajda.bakalarkasmokingapp.dashboard.wishmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.NotificationReceiver
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.NotificationScheduler
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.realmdatabase.RealmDB
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityEditWishBinding

class EditWishActivity : AppCompatActivity() {

    var position = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this,Data.actualTheme)

        val binding = ActivityEditWishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.editWishToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)


        position = intent.getIntExtra(Constants.extras.EXTRA_ITEM_ID,0)

        var wish = Data.wishList[position]

        binding.titleText.setText(wish.title.get().toString())
        binding.priceText.setText(wish.price.toString())
        binding.descText.setText(wish.desc.get())

//        Glide.with(edit_wish_image.context)
//            .load(wish.image)
//            .apply(RequestOptions().override(200,150))
//            .into(edit_wish_image)

        binding.saveWish.setOnClickListener {
            if( binding.titleText.text?.length == 0 ) {
                binding.titleTextLayout.setError(getString(R.string.error_name))
                binding.titleText.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun afterTextChanged(p0: Editable?) {
                        binding.titleTextLayout.setError(null)
                    }
                })
            }

            if( binding.priceText.text?.length == 0 ) {
                binding.priceTextLayout.setError(getString(R.string.error_price))
                binding.priceText.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun afterTextChanged(p0: Editable?) {
                        binding.priceTextLayout.setError(null)
                    }
                })
            }

            if( binding.descText.text?.length == 0 ) {
                binding.descTextLayout.setError(getString(R.string.error_desc))
                binding.descText.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun afterTextChanged(p0: Editable?) {
                        binding.descTextLayout.setError(null)
                    }
                })
            }

            try {
                if( binding.titleText.text?.length != 0 && binding.descText.text?.length != 0 && binding.priceText.text?.length != 0) { //&& edit_wish_image.resources != null
                    var price = binding.priceText.text.toString()
                    Data.wishList[position].setWish(binding.titleText.text.toString(), binding.descText.text.toString(), price.toInt(), Data.wishList[position].image!!)
                    var wish = Data.wishList[position]
                    RealmDB.updateWish(wish.id, wish)
                    var notificationScheduler = NotificationScheduler()
                    notificationScheduler.scheduleNotification(wish.title.get()!!, "Mozete si kupit svoje prianie", R.drawable.achievment_mission_twenty, applicationContext, 0, wish.endTime,true,false)

                    onBackPressed()
                }
            } catch (e:Exception) {
                Log.i("Saving error", e.message ?: "")
            }

        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.edit_wish_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
//                Glide.with(this).clear(edit_wish_image)
                onBackPressed()
                return true
            }
            R.id.delete -> {
                val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                val myIntent = Intent(applicationContext, NotificationReceiver::class.java)
                val pendingIntent = PendingIntent.getBroadcast(
                    applicationContext, 0, myIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT)
                alarmManager.cancel(pendingIntent)
                RealmDB.deleteWish(Data.wishList[position])
                Data.wishList.removeAt(position)
                onBackPressed()
            }


        }
        return super.onOptionsItemSelected(item)
    }
}

