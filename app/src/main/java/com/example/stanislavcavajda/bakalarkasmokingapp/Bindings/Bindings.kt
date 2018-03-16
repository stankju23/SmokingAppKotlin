package com.example.stanislavcavajda.bakalarkasmokingapp.Bindings

import android.databinding.BindingAdapter
import android.net.Uri
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

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
fun setImageUrl(imageView: ImageView, uri: Uri) {
    val context = imageView.getContext()
    Glide.with(context)
        .load(uri)
        .apply(RequestOptions().override(300,200))
        .into(imageView)
}