package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.NotificationScheduler
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.RealmDatabase.RealmDB
import kotlinx.android.synthetic.main.activity_edit_wish.*

class EditWishActivity : AppCompatActivity() {

    var position = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this,Data.actualTheme)

        setContentView(R.layout.activity_edit_wish)


        var toolbar = findViewById<Toolbar>(R.id.edit_wish_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)


        position = intent.getIntExtra(Constants.extras.EXTRA_ITEM_ID,0)

        var wish = Data.wishList[position]

        title_text.setText(wish.title.get().toString())
        price_text.setText(wish.price.toString())
        desc_text.setText(wish.desc.get())

        Glide.with(edit_wish_image.context)
            .load(wish.image)
            .apply(RequestOptions().override(200,150))
            .into(edit_wish_image)

        save_wish.setOnClickListener {
            if( title_text.text.length == 0 ) {
                title_text_layout.setError(getString(R.string.error_name))
                title_text.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun afterTextChanged(p0: Editable?) {
                        title_text_layout.setError(null)
                    }
                })
            }

            if( price_text.text.length == 0 ) {
                price_text_layout.setError(getString(R.string.error_price))
                price_text.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun afterTextChanged(p0: Editable?) {
                        price_text_layout.setError(null)
                    }
                })
            }

            if( desc_text.text.length == 0 ) {
                desc_text_layout.setError(getString(R.string.error_desc))
                desc_text.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun afterTextChanged(p0: Editable?) {
                        desc_text_layout.setError(null)
                    }
                })
            }

            try {
                if( title_text.text.length != 0 && desc_text.text.length != 0 && price_text.text.length != 0 && edit_wish_image.resources != null) {
                    var price = price_text.text.toString()
                    Data.wishList[position].setWish(title_text.text.toString(), desc_text.text.toString(), price.toInt(), Data.wishList[position].image!!)
                    var wish = Data.wishList[position]
                    RealmDB.updateWish(wish.id, wish)
                    var notificationScheduler = NotificationScheduler()
                    notificationScheduler.scheduleNotification(wish.title.get()!!, "Mozete si kupit svoje prianie", R.drawable.empty_stars, this, position, wish.endTime)

                    onBackPressed()
                }
            } catch (e:Exception) {
                Log.i("Saving error", e.message)
            }

        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_wish_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                Glide.with(this).clear(edit_wish_image)
                onBackPressed()
                return true
            }
            R.id.delete -> {
                RealmDB.deleteWish(Data.wishList[position])
                Data.wishList.removeAt(position)
                onBackPressed()
            }


        }
        return super.onOptionsItemSelected(item)
    }
}

