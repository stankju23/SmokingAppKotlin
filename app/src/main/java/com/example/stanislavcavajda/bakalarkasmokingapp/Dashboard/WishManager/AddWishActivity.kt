package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.RealmDB
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import kotlinx.android.synthetic.main.activity_add_wish.*
import java.util.UUID

class AddWishActivity : AppCompatActivity() {

    val RESULT_LOAD_IMAGE = 1
    var pickedImage:Bitmap? = null
    var imagePath: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_wish)

        add_wish_btn.setOnClickListener{
            if( title_text.text.length == 0 ) {
                title_text.setError("First name is required!")
            }

            if( price_text.text.length == 0 ) {
                price_text.setError("First name is required!")
            }

            if( desc.text.length == 0 ) {
                desc.setError("First name is required!")
            }

            if (add_wish_image.resources == null) {
                Log.i("nevybarl si ", "obrazok ")
            }

            if( title_text.text.length != 0 && desc.text.length != 0 && price_text.text.length != 0 ) {

                try {
                    var wish = Wish(UUID.randomUUID().toString(),title_text.text.toString(),desc.text.toString(),price_text.text.toString().toInt(),false,this,imagePath!!)
                    Log.i("Id",wish.id)
                    Data.wishList.add(wish)
                    RealmDB.addWishToDB(wish)
                    onBackPressed()
                } catch (e: Exception) {
                    Log.i("Error", e.message)
                    Toast.makeText(this,"Something went wrong try again",Toast.LENGTH_SHORT)
                }

            }


        }


        var toolbar = findViewById<Toolbar>(R.id.add_wish_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_material)

        add_wish_image.setOnClickListener {
            try {
                if (pickedImage == null) {
                    val i = Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(i, RESULT_LOAD_IMAGE)
                }
            } catch (e:Exception) {
                Log.i("Exception", "zrusil vyber obrazka")
            }

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.i("image" , "selected")

        var selectedImageUri = data?.getData()
        imagePath = selectedImageUri
        try {
            Glide.with(this)
                .load(selectedImageUri)
                .apply(RequestOptions().override(300,200))
                .into(add_wish_image)
        } catch (e:Exception) {
            Log.i("Error", e.message)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                Glide.with(this).clear(add_wish_image)
                onBackPressed()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}

