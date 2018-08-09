package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.RealmDatabase.RealmDB
import io.vrinda.kotlinpermissions.PermissionsActivity
import kotlinx.android.synthetic.main.activity_add_wish.*
import java.util.UUID

class AddWishActivity : PermissionsActivity() {

    val RESULT_LOAD_IMAGE = 1
    var pickedImage:Bitmap? = null
    var imagePath: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this,Data.actualTheme)

        setContentView(R.layout.activity_add_wish)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 100)
        }


        add_wish_btn.setOnClickListener{
            if( title_text.text.length == 0 ) {
                title_text.setError(getString(R.string.error_name))
            }

            if( price_text.text.length == 0 ) {
                price_text.setError(getString(R.string.error_price))
            }

            if( desc.text.length == 0 ) {
                desc.setError(getString(R.string.error_desc))
            }

            if (imagePath == null) {
                Toast.makeText(this,"Please choose image",Toast.LENGTH_SHORT).show()
            }

            if( title_text.text.length != 0 && desc.text.length != 0 && price_text.text.length != 0 && add_wish_image.resources != null) {

                try {
                    var wish = Wish(UUID.randomUUID().toString(),title_text.text.toString(),desc.text.toString(),price_text.text.toString().toInt(),false,this,imagePath!!)
                    Log.i("Id",wish.id)
                    Data.wishList.add(wish)
                    RealmDB.addWishToDB(wish)
                    onBackPressed()
                } catch (e: Exception) {
//                    if (e != null) {
//                        Log.i("Error", e.message)
//                    }
                    Toast.makeText(this,"Something went wrong try again",Toast.LENGTH_SHORT)
                }

            }


        }

        var toolbar = findViewById<Toolbar>(R.id.add_wish_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)

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

