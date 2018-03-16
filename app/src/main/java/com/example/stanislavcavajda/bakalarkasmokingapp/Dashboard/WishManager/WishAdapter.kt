package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager

import android.databinding.ViewDataBinding
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter

/**
 * Created by stanislavcavajda on 15/03/2018.
 */

class WishAdapter<T> : BindingRecyclerViewAdapter<T>() {

    override fun onBindBinding(binding: ViewDataBinding?, variableId: Int, layoutRes: Int, position: Int, item: T) {
        super.onBindBinding(binding, variableId, layoutRes, position, item)
        var image = binding?.root?.findViewById<ImageView>(R.id.circleImageView)
        Glide.with(image!!.context)
            .load((item as Wish).image)
            .apply(RequestOptions().override(300,200))
            .into(image!!)
        Log.i("Image", (item as Wish).image.toString())
    }


}