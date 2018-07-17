package com.example.stanislavcavajda.bakalarkasmokingapp.Cravings

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.Koloda.KolodaAdapter
import com.example.stanislavcavajda.bakalarkasmokingapp.Koloda.KolodaItem
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.SwipeDirection
import kotlinx.android.synthetic.main.activity_add_craving.*





class AddCravingActivity : AppCompatActivity() {

    lateinit var adapter:KolodaAdapter

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


        var kolodaItemList = ArrayList<KolodaItem>()

        for (i in 0..5) {
            kolodaItemList.add(KolodaItem(resources.getDrawable(R.drawable.koloda_item1),"Going out and running", "adsjvdvjashjvdasdhasjvhdvhjashjvgdhjvasjvhdjvghasjvgdasgdjvghasdjgvjadvascfjasdfhahsdjashdcjascjhasfcjasfc"))
        }

        adapter = KolodaAdapter(this,kolodaItemList)

        swipe_card.setAdapter(adapter)

        swipe_card.setCardEventListener(object : CardStackView.CardEventListener {

            override fun onCardDragging(percentX: Float, percentY: Float) {
            }

            override fun onCardSwiped(direction: SwipeDirection?) {
                try {
                    if (direction == SwipeDirection.Left) {
                        kolodaItemList[swipe_card.topIndex - 1].popularity--
                    } else {
                        kolodaItemList[swipe_card.topIndex - 1].popularity--
                    }
                    if (swipe_card.topIndex == kolodaItemList.size) {
                        finish()
                    }
                } catch (e:Exception) { }
            }

            override fun onCardReversed() {
            }

            override fun onCardMovedToOrigin() {
            }

            override fun onCardClicked(index: Int) {
            }
        })

        like.setOnClickListener {
            swipeRight()
        }

        dislike.setOnClickListener {
            swipeLeft()
        }



    }

    private fun extractRemainingTouristSpots(): ArrayList<KolodaItem> {
        val spots = ArrayList<KolodaItem>()
        for (i in swipe_card.getTopIndex() until adapter.getCount()) {
            spots.add(adapter.getItem(i) as KolodaItem)
        }
        return spots
    }

    fun swipeLeft() {
        val spots = extractRemainingTouristSpots()
        if (spots.isEmpty()) {
            return
        }

        val target = swipe_card.getTopView()
        val targetOverlay = swipe_card.getTopView().getOverlayContainer()

        val rotation = ObjectAnimator.ofPropertyValuesHolder(
            target, PropertyValuesHolder.ofFloat("rotation", -10f))
        rotation.duration = 200
        val translateX = ObjectAnimator.ofPropertyValuesHolder(
            target, PropertyValuesHolder.ofFloat("translationX", 0f, -2000f))
        val translateY = ObjectAnimator.ofPropertyValuesHolder(
            target, PropertyValuesHolder.ofFloat("translationY", 0f, 500f))
        translateX.startDelay = 100
        translateY.startDelay = 100
        translateX.duration = 500
        translateY.duration = 500
        val cardAnimationSet = AnimatorSet()
        cardAnimationSet.playTogether(rotation, translateX, translateY)

        val overlayAnimator = ObjectAnimator.ofFloat(targetOverlay, "alpha", 0f, 1f)
        overlayAnimator.duration = 200
        val overlayAnimationSet = AnimatorSet()
        overlayAnimationSet.playTogether(overlayAnimator)

        swipe_card.swipe(SwipeDirection.Left, cardAnimationSet, overlayAnimationSet)
    }

    fun swipeRight() {
        val spots = extractRemainingTouristSpots()
        if (spots.isEmpty()) {
            return
        }

        val target = swipe_card.getTopView()
        val targetOverlay = swipe_card.getTopView().getOverlayContainer()

        val rotation = ObjectAnimator.ofPropertyValuesHolder(
            target, PropertyValuesHolder.ofFloat("rotation", 10f))
        rotation.duration = 200
        val translateX = ObjectAnimator.ofPropertyValuesHolder(
            target, PropertyValuesHolder.ofFloat("translationX", 0f, 2000f))
        val translateY = ObjectAnimator.ofPropertyValuesHolder(
            target, PropertyValuesHolder.ofFloat("translationY", 0f, 500f))
        translateX.startDelay = 100
        translateY.startDelay = 100
        translateX.duration = 500
        translateY.duration = 500
        val cardAnimationSet = AnimatorSet()
        cardAnimationSet.playTogether(rotation, translateX, translateY)

        val overlayAnimator = ObjectAnimator.ofFloat(targetOverlay, "alpha", 0f, 1f)
        overlayAnimator.duration = 200
        val overlayAnimationSet = AnimatorSet()
        overlayAnimationSet.playTogether(overlayAnimator)

        swipe_card.swipe(SwipeDirection.Right, cardAnimationSet, overlayAnimationSet)
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
