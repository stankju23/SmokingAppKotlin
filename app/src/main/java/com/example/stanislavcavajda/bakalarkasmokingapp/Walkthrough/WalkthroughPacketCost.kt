package com.example.stanislavcavajda.bakalarkasmokingapp.Walkthrough

import android.graphics.drawable.Drawable

class WalkthroughPacketCost {

    var image: Drawable
    var text: String
    var price: Double
    var buttonText: String

    constructor( image: Drawable, text:String, price:Double, buttonText:String) {
        this.image = image
        this.text = text
        this.price = price
        this.buttonText = buttonText
    }
}