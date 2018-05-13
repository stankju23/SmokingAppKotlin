package com.example.stanislavcavajda.bakalarkasmokingapp.Cravings

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.Koloda.KolodaClass
import com.example.stanislavcavajda.bakalarkasmokingapp.Koloda.KolodaItem
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.mindorks.placeholderview.SwipeDecor
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.SwipeViewBuilder
import kotlinx.android.synthetic.main.activity_add_craving.*


class AddCravingActivity : AppCompatActivity() {

    var latitude:Double = 0.0
    var longitude:Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.setTheme(this, Data.actualTheme)
        setContentView(R.layout.activity_add_craving)



        var toolbar = findViewById<Toolbar>(R.id.add_craving_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_cancel)
        supportActionBar?.title = resources.getString(R.string.first_aid)


        var swipeCard = findViewById<SwipePlaceHolderView>(R.id.swipe_card)

        swipeCard.getBuilder<SwipePlaceHolderView,SwipeViewBuilder<SwipePlaceHolderView>>()
                .setDisplayViewCount(3)
                .setSwipeDecor(SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.swipe_in_message)
                        .setSwipeOutMsgLayoutId(R.layout.swipe_out_message))


        for (i in 0..5) {
            var kolodaItem = KolodaClass(this, KolodaItem(resources.getDrawable(R.drawable.koloda_item1),"Going out and running", "adsjvdvjashjvdasdhasjvhdvhjashjvgdhjvasjvhdjvghasjvgdasgdjvghasdjgvjadvascfjasdfhahsdjashdcjascjhasfcjasfc"),swipeCard)
            swipeCard.addView(kolodaItem)
        }

        swipeCard.addItemRemoveListener { if (it == 0) { onBackPressed() } }

        like.setOnClickListener {
            swipeCard.doSwipe(true)

        }

        dislike.setOnClickListener {
            swipeCard.doSwipe(false)
        }



    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_craving, menu)
        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            R.id.repeat -> {
                recreate()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {

        super.onResume()
    }

    override fun onPause() {

        super.onPause()
    }

    override fun onStop()
    {

        super.onStop()
    }

}
