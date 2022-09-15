package com.example.stanislavcavajda.bakalarkasmokingapp.cravings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.koloda.KolodaItem
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.realmdatabase.RealmDB
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityAddCardBinding
import java.util.UUID

class AddCardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this, Data.actualTheme)

        var binding = ActivityAddCardBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var toolbar = findViewById<Toolbar>(R.id.add_card_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)

        binding.addCard.setOnClickListener {

            if( binding.cardTitle.text.length == 0 ) {
                binding.cardTitle.setError(getString(R.string.error_name))
            }

            if( binding.cardDesc.text.length == 0 ) {
                binding.cardDesc.setError(getString(R.string.error_desc))
            }

            if (binding.cardTitle.text.length != 0 && binding.cardDesc.text.length != 0) {

                var kolodaItem = KolodaItem(UUID.randomUUID().toString(), 4, binding.cardTitle.text.toString(), binding.cardDesc.text.toString(), this)
                Data.cravingsCardList.add(kolodaItem)
                this.finish()
                RealmDB.addCard(kolodaItem)
            }
        }

    }
}
