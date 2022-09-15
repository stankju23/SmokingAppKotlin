package com.example.stanislavcavajda.bakalarkasmokingapp.cravings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.ThemeManager
import com.example.stanislavcavajda.bakalarkasmokingapp.koloda.KolodaAdapter
import com.example.stanislavcavajda.bakalarkasmokingapp.koloda.KolodaItem
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.ActivityAddCravingBinding

class AddCravingActivity : AppCompatActivity() {

    lateinit var adapter:KolodaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager.setTheme(this, Data.actualTheme)
        val binding = ActivityAddCravingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.addCravingToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_cancel)
        supportActionBar?.title = resources.getString(R.string.first_aid)


        var sortedList = Data.cravingsCardList.sortedByDescending({selector(it)} )
        var kolodaItemList = ArrayList<KolodaItem>()
        kolodaItemList.addAll(sortedList)

        adapter = KolodaAdapter(this,kolodaItemList)

//        swipe_card.setAdapter(adapter)

//        swipe_card.setCardEventListener(object : CardStackView.CardEventListener {

//            override fun onCardDragging(percentX: Float, percentY: Float) {
//            }
//
//            override fun onCardSwiped(direction: SwipeDirection?) {
//                try {
//                    if (direction == SwipeDirection.Left) {
//                        for (item in Data.cravingsCardList) {
//                            if (item.id == kolodaItemList[swipe_card.topIndex-1].id) {
//                                item.popularity--
//                                RealmDB.updateCard(item)
//                            }
//                        }
//                    } else {
//                        for (item in Data.cravingsCardList) {
//                            if (item.id == kolodaItemList[swipe_card.topIndex-1].id) {
//                                item.popularity++
//                                RealmDB.updateCard(item)
//                            }
//                        }
//                    }
//                    if (swipe_card.topIndex == kolodaItemList.size) {
//                        empty_state_layout.visibility = View.VISIBLE
//                    }
//                } catch (e:Exception) { }
//            }
//
//            override fun onCardReversed() {
//            }
//
//            override fun onCardMovedToOrigin() {
//            }
//
//            override fun onCardClicked(index: Int) {
//            }
//        })
//
//        like.setOnClickListener {
//            swipeRight()
//        }
//
//        dislike.setOnClickListener {
//            swipeLeft()
//        }
//
//        add_card.setOnClickListener {
//            var addCardActivity = Intent(this,AddCardActivity::class.java)
//            startActivity(addCardActivity)
//            this.finish()
//        }

    }

    fun selector(p: KolodaItem): Int = p.popularity

//    private fun extractRemainingTouristSpots(): ArrayList<KolodaItem> {
//        val spots = ArrayList<KolodaItem>()
//        for (i in swipe_card.getTopIndex() until adapter.getCount()) {
//            spots.add(adapter.getItem(i) as KolodaItem)
//        }
//        return spots
//    }

//    fun swipeLeft() {
//
//        like.isEnabled = false
//        dislike.isEnabled = false
//        var handler = Handler()
//        handler.postDelayed(Runnable {
//            like.isEnabled = true
//            dislike.isEnabled = true
//        },500)
//
//        val spots = extractRemainingTouristSpots()
//        if (spots.isEmpty()) {
//            return
//        }
//
//        val target = swipe_card.getTopView()
//        val targetOverlay = swipe_card.getTopView().getOverlayContainer()
//        val rotation = ObjectAnimator.ofPropertyValuesHolder(
//            target, PropertyValuesHolder.ofFloat("rotation", -10f))
//        rotation.duration = 200
//        val translateX = ObjectAnimator.ofPropertyValuesHolder(
//            target, PropertyValuesHolder.ofFloat("translationX", 0f, -2000f))
//        val translateY = ObjectAnimator.ofPropertyValuesHolder(
//            target, PropertyValuesHolder.ofFloat("translationY", 0f, 500f))
//        translateX.startDelay = 100
//        translateY.startDelay = 100
//        translateX.duration = 500
//        translateY.duration = 500
//        val cardAnimationSet = AnimatorSet()
//        cardAnimationSet.playTogether(rotation, translateX, translateY)
//
//        val overlayAnimator = ObjectAnimator.ofFloat(targetOverlay, "alpha", 0f, 1f)
//        overlayAnimator.duration = 200
//        val overlayAnimationSet = AnimatorSet()
//        overlayAnimationSet.playTogether(overlayAnimator)
//
//        swipe_card.swipe(SwipeDirection.Left, cardAnimationSet, overlayAnimationSet)
//
//
//    }

//    fun swipeRight() {
//
//        like.isEnabled = false
//        dislike.isEnabled = false
//        var handler = Handler()
//        handler.postDelayed(Runnable {
//            like.isEnabled = true
//            dislike.isEnabled = true
//        },500)
//
//        val spots = extractRemainingTouristSpots()
//        if (spots.isEmpty()) {
//            return
//        }
//
//        val target = swipe_card.getTopView()
//        val targetOverlay = swipe_card.getTopView().getOverlayContainer()
//
//        val rotation = ObjectAnimator.ofPropertyValuesHolder(
//            target, PropertyValuesHolder.ofFloat("rotation", 10f))
//        rotation.duration = 200
//        val translateX = ObjectAnimator.ofPropertyValuesHolder(
//            target, PropertyValuesHolder.ofFloat("translationX", 0f, 2000f))
//        val translateY = ObjectAnimator.ofPropertyValuesHolder(
//            target, PropertyValuesHolder.ofFloat("translationY", 0f, 500f))
//        translateX.startDelay = 100
//        translateY.startDelay = 100
//        translateX.duration = 500
//        translateY.duration = 500
//        val cardAnimationSet = AnimatorSet()
//        cardAnimationSet.playTogether(rotation, translateX, translateY)
//
//        val overlayAnimator = ObjectAnimator.ofFloat(targetOverlay, "alpha", 0f, 1f)
//        overlayAnimator.duration = 200
//        val overlayAnimationSet = AnimatorSet()
//        overlayAnimationSet.playTogether(overlayAnimator)
//
//        swipe_card.swipe(SwipeDirection.Right, cardAnimationSet, overlayAnimationSet)
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.add_craving, menu)
        return super.onCreateOptionsMenu(menu)
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
