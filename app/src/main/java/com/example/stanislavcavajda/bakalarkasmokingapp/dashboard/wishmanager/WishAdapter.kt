package com.example.stanislavcavajda.bakalarkasmokingapp.dashboard.wishmanager

import androidx.databinding.ViewDataBinding
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter

/**
 * Created by stanislavcavajda on 15/03/2018.
 */

class WishAdapter<T> : BindingRecyclerViewAdapter<T>() {


    override fun onBindBinding(binding: ViewDataBinding?, variableId: Int, layoutRes: Int, position: Int, item: T) {
        super.onBindBinding(binding, variableId, layoutRes, position, item)
//        swipeLayout?.surfaceView?.setOnClickListener {
//            if (!Data.wishList[position].isBought.get()) {
//                var editWish = Intent(swipeLayout?.context, EditWishActivity::class.java)
//                editWish.putExtra(Constants.extras.EXTRA_ITEM_ID, position)
//
////                var image = (swipeLayout?.context as Activity).findViewById<ImageView>(R.id.circleImageView)
////                var title = (swipeLayout?.context as Activity).findViewById<TextView>(R.id.wish_list_item_title)
////                var price = (swipeLayout?.context as Activity).findViewById<TextView>(R.id.wish_list_item_price)
////                var desc = (swipeLayout?.context as Activity).findViewById<TextView>(R.id.wish_list_item_desc)
////
////                var p1 = Pair.create(image as View, "image")
////                var p2 = Pair.create(title as View, "title")
////                var p3 = Pair.create(price as View, "price")
////                var p4 = Pair.create(desc as View, "desc")
////
////                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(swipeLayout?.context as Activity,p1,p2,p3,p4)
//                (swipeLayout?.context as Activity).startActivity(editWish)
//            }
//        }

    }


}