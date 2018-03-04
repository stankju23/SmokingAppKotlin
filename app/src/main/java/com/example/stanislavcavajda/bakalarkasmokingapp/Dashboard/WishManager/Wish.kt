package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager

import android.graphics.drawable.Drawable

/**
 * Created by stanislavcavajda on 04/03/2018.
 */
class Wish {

    var id: Int
    var title: String
    var desc: String
    var price: Int
    var image: Drawable
    var canBuy: Boolean
    var isBought: Boolean

    constructor(id: Int,title: String, desc: String, price: Int, image: Drawable, canBuy: Boolean, bought: Boolean) {
        this.id = id
        this.title = title
        this.desc = desc
        this.price = price
        this.image = image
        this.canBuy = canBuy
        this.isBought = bought
    }

    fun setWish(id:Int,title: String, desc: String, price: Int, image: Drawable, canBuy: Boolean, bought: Boolean): Wish {
        var wish: Wish = Wish(id,title, desc, price, image, canBuy, bought)
        return wish
    }
}