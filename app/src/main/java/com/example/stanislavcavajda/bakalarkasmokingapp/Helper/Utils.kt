package com.example.stanislavcavajda.bakalarkasmokingapp.Helper

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager




/**
 * Created by janisharali on 20/08/16.
 */
object Utils {

    /**
     *
     * @return
     */
     val isAboveApi21: Boolean
        get() = Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP

    /**
     *
     * @param context
     * @return
     */
    internal fun getDeviceWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    /**
     *
     * @param context
     * @return
     */
    internal fun getDeviceHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }

    /**
     *
     * @param context
     * @param dp
     * @return
     */
    fun dpToPx(context: Context, dp: Int): Int {
        return (dp * context.resources.displayMetrics.density).toInt()
    }

    fun dpToPx(dp: Float): Int {
        val density = Resources.getSystem().displayMetrics.density
        return Math.round(dp * density)
    }

    fun getDisplaySize(windowManager: WindowManager): Point {
        try {
            if (Build.VERSION.SDK_INT > 16) {
                val display = windowManager.defaultDisplay
                val displayMetrics = DisplayMetrics()
                display.getMetrics(displayMetrics)
                return Point(displayMetrics.widthPixels, displayMetrics.heightPixels)
            } else {
                return Point(0, 0)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return Point(0, 0)
        }
    }

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }
}
