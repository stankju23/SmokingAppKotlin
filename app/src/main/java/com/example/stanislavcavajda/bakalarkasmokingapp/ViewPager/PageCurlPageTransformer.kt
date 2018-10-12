package com.example.stanislavcavajda.bakalarkasmokingapp.ViewPager

import android.support.v4.view.ViewPager
import android.view.View

class PageCurlPageTransformer : ViewPager.PageTransformer {

    override fun transformPage(page: View, position: Float) {

        if (page is PageCurl) {
            if (position > -1.0f && position < 1.0f) {
                // hold the page steady and let the views do the work
                page.translationX = -position * page.width
            } else {
                page.translationX = 0.0f
            }
            if (position <= 1.0f && position >= -1.0f) {
                (page as PageCurl).setCurlFactor(position)
            }
        }
    }
}