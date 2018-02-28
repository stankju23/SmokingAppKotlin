package com.example.stanislavcavajda.bakalarkasmokingapp.Main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.ObservableField
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import com.github.bluzwong.swipeback.SwipeBackActivityHelper

/**
 * Created by stanislavcavajda on 19/02/2018.
 */
class ToolbarViewModel {

    var leftImage: ObservableField<Drawable> = ObservableField()
    var rightImage: ObservableField<Drawable> = ObservableField()
    var title: ObservableField<String> = ObservableField()
    var context: Context? = null


    constructor(lefImage:Drawable?, rightImage: Drawable?, title: String, context: Context) {
        this.leftImage.set(lefImage)
        this.rightImage.set(rightImage)
        this.title.set(title)
        this.context = context
    }

    fun setTitle(title: String) {
        this.title.set(title)
    }

    fun setLeftImage(image:Drawable?) {
        this.leftImage.set(image)
    }

    fun setRightImage(image:Drawable?) {
        this.rightImage.set(image)
    }

    fun openSettings(v:View) {
        if(v.background != null) {
            Log.i("resource", "not null")
            var settingsActivity = Intent(context, SettingsActivity::class.java)
            SwipeBackActivityHelper.activityBuilder(context as Activity)
                    .intent(settingsActivity)
                    .needParallax(true)
                    .needBackgroundShadow(true)
                    .startActivity();
        } else {
            Log.i("resource", "null")
        }

    }
}