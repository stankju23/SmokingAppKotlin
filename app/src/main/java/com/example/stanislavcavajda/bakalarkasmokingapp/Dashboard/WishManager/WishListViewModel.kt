package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.view.View
import com.example.stanislavcavajda.bakalarkasmokingapp.BR
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Created by stanislavcavajda on 04/03/2018.
 */
class WishListViewModel: BaseObservable {

    var wishList: ObservableArrayList<Wish> = ObservableArrayList()
    var actualWish: ObservableField<Wish> = ObservableField()
    var context: Context
    var itemBinding : ItemBinding<Wish> = ItemBinding.of(BR.viewModel, R.layout.wish_list_item)

    constructor(wishList: ArrayList<Wish>, context: Context) {
        this.wishList.addAll(wishList)
        updateWish()
        this.context = context
    }

    fun updateWishList(newWishList: ArrayList<Wish>) {

        if (wishList.size == newWishList.size) {
            for (i in 0..wishList.size-1) {
                wishList.set(i,newWishList.get(i))
            }
        }

        if (wishList.size < newWishList.size) {
            for (i in 0..newWishList.size) {
                if (i <= wishList.size) {
                    wishList.set(i,newWishList.get(i))
                } else {
                    wishList.add(newWishList.get(i))
                }
            }
        }

        updateWish()

    }

    fun updateWish() {
        var actualWish: Wish? = null
        for (item in wishList) {
            if (!item.canBuy && !item.isBought) {
                actualWish = item
                break
            }
        }
        this.actualWish.set(actualWish)
    }

    fun showWishes(v:View) {
        var wishes = Intent(context,WishesActivity::class.java)
        (context as Activity).startActivity(wishes)
    }
}