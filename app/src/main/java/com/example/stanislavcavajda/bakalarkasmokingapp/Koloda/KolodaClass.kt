package com.example.stanislavcavajda.bakalarkasmokingapp.Koloda

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.mindorks.placeholderview.SwipeDirection
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.annotations.View
import com.mindorks.placeholderview.annotations.swipe.*







/**
 * Created by stanislavcavajda on 10/05/2018.
 */

@Layout(R.layout.koloda_card)
class KolodaClass {

    @View(R.id.category_image)
    lateinit var categoryImage:ImageView

    @View(R.id.card_title)
    lateinit var cardTitle:TextView

    @View(R.id.card_desc)
    lateinit var cardDesc:TextView

    var context:Context
    var item:KolodaItem
    var swipeView:SwipePlaceHolderView

    constructor(context: Context,item:KolodaItem, swipeView: SwipePlaceHolderView) {
        this.context = context
        this.item = item
        this.swipeView = swipeView
    }

    @Resolve
    fun onResolved() {
        categoryImage.setImageDrawable(item.image)
        cardTitle.setText(item.title)
        cardDesc.setText(item.desc)
    }


    @SwipeOutDirectional
    private fun onSwipeOutDirectional(direction: SwipeDirection) {
        item.popularity--
    }

    @SwipeInDirectional
    private fun onSwipeInDirectional(direction: SwipeDirection) {
        item.popularity++
    }

    @SwipeOut
    private fun onSwipedOut() {
        Log.d("EVENT", "onSwipedOut")
    }

    @SwipeCancelState
    private fun onSwipeCancelState() {
        Log.d("EVENT", "onSwipeCancelState")
    }

    @SwipeIn
    private fun onSwipeIn() {
        Log.d("EVENT", "onSwipedIn")
    }

    @SwipeInState
    private fun onSwipeInState() {
        Log.d("EVENT", "onSwipeInState")
    }

    @SwipeOutState
    private fun onSwipeOutState() {
        Log.d("EVENT", "onSwipeOutState")
    }


}