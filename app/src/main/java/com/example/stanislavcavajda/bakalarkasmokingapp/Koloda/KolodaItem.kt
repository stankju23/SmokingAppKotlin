package com.example.stanislavcavajda.bakalarkasmokingapp.Koloda

import android.graphics.drawable.Drawable

/**
 * Created by stanislavcavajda on 09/05/2018.
 */
class KolodaItem  {

    var image: Drawable
    var title:String
    var desc:String
    var popularity:Int = 0


    constructor(image:Drawable,title:String,desc:String){
        this.image = image
        this.title = title
        this.desc = desc
    }
}