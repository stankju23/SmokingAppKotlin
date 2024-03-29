package com.example.stanislavcavajda.bakalarkasmokingapp.Bindings

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.net.Uri
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.CardView
import android.util.TypedValue
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.daimajia.swipe.SwipeLayout
import com.example.stanislavcavajda.bakalarkasmokingapp.R

/**
 * Created by stanislavcavajda on 10/03/2018.
 */
@BindingAdapter("blink")
fun setBlink(frameLayout: FrameLayout, bool:Boolean) {
    if (bool == true) {
        val blinkanimation = AlphaAnimation(1f, 0f) // Change alpha from fully visible to invisible
        blinkanimation.setDuration(800) // duration
        blinkanimation.setInterpolator(LinearInterpolator()) // do not alter animation rate
        blinkanimation.setRepeatCount(Animation.INFINITE) // Repeat animation infinitely
        blinkanimation.setRepeatMode(Animation.REVERSE)
        frameLayout.startAnimation(blinkanimation)
    }
}

@BindingAdapter("imageUri")
fun setImageUrl(imageView: ImageView, uri: Uri?) {
    val context = imageView.getContext()
    if (uri != null) {
        Glide.with(context)
            .load(uri)
            .apply(RequestOptions().override(200, 150))
            .into(imageView)
    } else {
        Glide.with(context).clear(imageView)
    }
}


@BindingAdapter("first_done")
fun setFirstDone(layout:ConstraintLayout, done:Boolean) {
    val density = layout.context.getResources().getDisplayMetrics().density
    var dp = 56 * density
    if (done) {
        layout.translationY = layout.translationY + dp
    }
}


@BindingAdapter("done")
fun setDone(layout:ConstraintLayout, done:Boolean) {

    val typedValue = TypedValue()
    val theme = layout.context.getTheme()
    theme.resolveAttribute(R.attr.colorPrimary, typedValue, true)
    var color = typedValue.data

    val density = layout.context.getResources().getDisplayMetrics().density
    var dp = 56 * density
    if (!done){
        layout.setBackgroundColor(layout.context.resources.getColor(R.color.light_grey))
        if (layout.y != 0f) {
            layout.translationY = dp
            layout.animate().translationYBy(-dp).duration = 200
        }
    }
    if (done) {
        layout.setBackgroundColor(color)
        if (layout.y == 0f) {
            layout.translationY = 0f
            layout.animate().translationYBy(dp).duration = 200
        } else {
            layout.translationY = layout.translationY + dp
        }
    }
}

@BindingAdapter("move")
fun setMove(layout:ScrollView, done:Boolean) {
    val density = layout.context.getResources().getDisplayMetrics().density
    var dp = 56 * density
    if (!done){
        layout.translationY = dp
        layout.animate().translationYBy(-dp).duration = 200
    }
    if (done) {
        layout.animate().translationYBy(dp).duration = 200
    }
}



@BindingAdapter("bindDate")
fun bindDate(textview:TextView,date:String){
    textview.setText("dasdiuasidbasduiasbdasbiasbd")
}


@BindingAdapter("imageSrc")
fun setImageSrc(imageView: ImageView,image:Drawable) {
    val context = imageView.getContext()
    if (image != null) {
        Glide.with(context)
            .load(image)
            .into(imageView)
    } else {
        Glide.with(context).clear(imageView)
    }
}


@BindingAdapter("swiping")
fun disableSwiping(layout: SwipeLayout,canBuy:Boolean) {
    if (canBuy) {
        layout.isSwipeEnabled = true
    } else {
        layout.isSwipeEnabled = false
    }
}

@BindingAdapter("colourText")
fun setColourText(textView:TextView,isDone:Boolean) {
    val value = TypedValue()
    textView.context.getTheme().resolveAttribute(R.attr.colorPrimary, value, true)
    if (isDone){
        textView.setTextColor(value.data)
    } else {
        textView.setTextColor(textView.context.resources.getColor(R.color.black))
    }

}

@BindingAdapter("setBackground")
fun setBackground(layout: ConstraintLayout,isDone:Boolean) {
    if (isDone){
        val value = TypedValue()
        layout.context.getTheme().resolveAttribute(R.attr.colorPrimary, value, true)
        layout.setBackgroundColor(value.data)
    } else {
        val value = TypedValue()
        layout.context.getTheme().resolveAttribute(R.attr.colorLight, value, true)
        layout.setBackgroundColor(value.data)
    }

}

@BindingAdapter("availableColor", "lockedColor")
fun setTextColor(textView:TextView, locked:Boolean, available:Boolean){
    if (locked) {
        textView.setTextColor(textView.context.resources.getColor(R.color.white))
    } else {
        if (available) {
            textView.setTextColor(textView.context.resources.getColor(R.color.white))
        } else {
            val value = TypedValue()
            textView.context.getTheme().resolveAttribute(R.attr.colorAccent, value, true)
            textView.setTextColor(value.data)
        }
    }
}

@BindingAdapter("cardLockedColor","writtenColor")
fun setJournalCardColor(layout:CardView, locked:Boolean, written:Boolean){
    if (locked == true) {
        layout.setCardBackgroundColor(layout.context.resources.getColor(R.color.light_grey))
    } else {
        if (written == true) {
            val value = TypedValue()
            layout.context.getTheme().resolveAttribute(R.attr.colorAccent, value, true)
            layout.setCardBackgroundColor(value.data)
        } else {
            val value = TypedValue()
            layout.context.getTheme().resolveAttribute(R.attr.colorPrimary, value, true)
            layout.setCardBackgroundColor(value.data)
        }
    }
}

