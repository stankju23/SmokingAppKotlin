package com.example.stanislavcavajda.bakalarkasmokingapp.Koloda

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import com.example.stanislavcavajda.bakalarkasmokingapp.R

/**
 * Created by stanislavcavajda on 09/05/2018.
 */
class KolodaItem  {

    var id:String
    lateinit var image: Drawable
    var title:String
    var desc:String
    var popularity:Int = 0
    var context:Context
    var category:Int = 0


    constructor(id:String,category:Int,title:String,desc:String,context: Context){
        this.id = id
        this.context = context
        this.category = category
        when(this.category) {
            0 -> this.image = (context as Activity).resources.getDrawable(R.drawable.koloda_item1)
            1 -> this.image = (context as Activity).resources.getDrawable(R.drawable.koloda_item2)
            2 -> this.image = (context as Activity).resources.getDrawable(R.drawable.koloda_item3)
            3 -> this.image = (context as Activity).resources.getDrawable(R.drawable.koloda_item4)
            4 -> this.image = (context as Activity).resources.getDrawable(R.drawable.ic_marker)
            else -> {
                this.image = (context as Activity).resources.getDrawable(R.drawable.ic_marker)
            }
        }
        this.title = title
        this.desc = desc
    }
}