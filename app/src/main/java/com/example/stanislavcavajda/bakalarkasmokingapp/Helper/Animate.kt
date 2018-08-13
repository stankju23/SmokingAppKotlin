package com.example.stanislavcavajda.bakalarkasmokingapp.Helper

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import com.example.stanislavcavajda.bakalarkasmokingapp.R

object Animate {

    fun shakeAnim(v:View,context: Context) {
        var shakeAnim = AnimationUtils.loadAnimation(context, R.anim.shake)
        v.startAnimation(shakeAnim)
    }
}