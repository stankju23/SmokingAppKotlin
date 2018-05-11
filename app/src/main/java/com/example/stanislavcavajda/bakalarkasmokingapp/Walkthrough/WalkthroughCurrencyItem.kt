package com.example.stanislavcavajda.bakalarkasmokingapp.Walkthrough

import android.graphics.drawable.Drawable

class WalkthroughCurrencyItem {

    var title: String
    var image: Drawable
    var text: String
    var currency: ArrayList<String>
    var buttonText: String

    constructor(title: String, image: Drawable, text:String, currency:ArrayList<String>, buttonText:String) {
        this.title = title
        this.image = image
        this.text = text
        this.currency = currency
        this.buttonText = buttonText
    }
}
