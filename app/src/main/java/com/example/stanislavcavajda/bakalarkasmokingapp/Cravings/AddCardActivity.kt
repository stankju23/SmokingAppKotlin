package com.example.stanislavcavajda.bakalarkasmokingapp.Cravings

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.Koloda.KolodaItem
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.RealmDatabase.RealmDB
import kotlinx.android.synthetic.main.activity_add_card.*
import java.util.UUID

class AddCardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ThemeManager.setTheme(this, Data.actualTheme)

        setContentView(R.layout.activity_add_card)


        var toolbar = findViewById<Toolbar>(R.id.add_card_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)

        add_card.setOnClickListener {

            if( card_title.text.length == 0 ) {
                card_title.setError(getString(R.string.error_name))
            }

            if( card_desc.text.length == 0 ) {
                card_desc.setError(getString(R.string.error_desc))
            }

            if (card_title.text.length != 0 && card_desc.text.length != 0) {

                var kolodaItem = KolodaItem(UUID.randomUUID().toString(), 4, card_title.text.toString(), card_desc.text.toString(), this)
                Data.cravingsCardList.add(kolodaItem)
                this.finish()
                RealmDB.addCard(kolodaItem)
            }
        }

    }
}
