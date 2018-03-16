package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.RealmDB
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import kotlinx.android.synthetic.main.activity_edit_wish.*

class EditWishActivity : AppCompatActivity() {

    var position = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_wish)

        var toolbar = findViewById<Toolbar>(R.id.edit_wish_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_material)


        position = intent.getIntExtra(Constants.extras.EXTRA_ITEM_ID,0)

        var wish = Data.wishList[position]

        title_text.setText(wish.title.toString())
        price_text.setText(wish.price.toString())

        Glide.with(edit_wish_image.context)
            .load(wish.image)
            .apply(RequestOptions().override(200,150))
            .into(edit_wish_image)

        save_wish.setOnClickListener {
            try {
                var price = price_text.text.toString()
                Data.wishList[position].setWish(title_text.text.toString(),desc.text.toString(),price.toInt(),Data.wishList[position].image!!)
                var wish = Data.wishList[position]
                RealmDB.updateWish(wish.id,wish)
                onBackPressed()
            } catch (e:Exception) {
                Log.i("Saving error", e.message)
            }

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                Glide.with(this).clear(edit_wish_image)
                onBackPressed()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}

