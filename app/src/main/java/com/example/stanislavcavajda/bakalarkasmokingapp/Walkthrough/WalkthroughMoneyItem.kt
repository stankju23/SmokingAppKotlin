package com.example.stanislavcavajda.bakalarkasmokingapp.Walkthrough

import android.graphics.drawable.Drawable
import android.view.View

class WalkthroughMoneyItem {

    var image: Drawable
    var text: String
    var price: Int
    var buttonText: String

    constructor( image:Drawable, text:String, price:Int, buttonText:String) {
        this.image = image
        this.text = text
        this.price = price
        this.buttonText = buttonText
    }

    fun addPrice(v:View) {
        this.price ++
    }

    fun subPrice(v:View) {
        if (this.price > 0) {
            this.price --
        }
    }

}