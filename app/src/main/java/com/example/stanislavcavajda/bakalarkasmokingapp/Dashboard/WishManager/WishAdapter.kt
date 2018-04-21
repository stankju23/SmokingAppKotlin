package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager

import android.content.Intent
import android.databinding.ViewDataBinding
import com.daimajia.swipe.SwipeLayout
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter

/**
 * Created by stanislavcavajda on 15/03/2018.
 */

class WishAdapter<T> : BindingRecyclerViewAdapter<T>() {


    override fun onBindBinding(binding: ViewDataBinding?, variableId: Int, layoutRes: Int, position: Int, item: T) {
        super.onBindBinding(binding, variableId, layoutRes, position, item)
        var swipeLayout = binding?.root?.findViewById<SwipeLayout>(R.id.wish_item_swipe_layout)
        swipeLayout?.surfaceView?.setOnClickListener {
            if (!Data.wishList[position].isBought.get()) {
                var editWish = Intent(swipeLayout?.context, EditWishActivity::class.java)
                editWish.putExtra(Constants.extras.EXTRA_ITEM_ID, position)
                swipeLayout?.context.startActivity(editWish)
            }
        }

    }


}