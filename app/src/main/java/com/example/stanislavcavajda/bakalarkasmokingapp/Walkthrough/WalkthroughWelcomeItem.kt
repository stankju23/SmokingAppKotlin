package com.example.stanislavcavajda.bakalarkasmokingapp.Walkthrough

import android.graphics.drawable.Drawable

class WalkthroughWelcomeItem {

    var title: String
    var image: Drawable
    var text: String
    var buttonText: String

    constructor(title: String, image: Drawable, text:String, buttonText:String) {
        this.title = title
        this.image = image
        this.text = text
        this.buttonText = buttonText
    }
}