package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.util.Log
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
    var adapter: WishAdapter<Wish> = WishAdapter()

    lateinit var context: Context


    var itemBinding : ItemBinding<Wish> = ItemBinding.of(BR.viewModel, R.layout.wish_list_item)

    constructor(wishList: ArrayList<Wish>, context: Context) {

        this.wishList.addAll(wishList)
        updateWish()
        this.context = context


    }

    fun updateWishList(newWishList: ArrayList<Wish>) {
        this.wishList.clear()
        this.wishList.addAll(newWishList)
        updateWish()
    }

    fun updateWish() {
        var actualWish: Wish? = null
        for (item in wishList) {
            if (!item.canBuy.get() && !item.isBought.get()) {
                actualWish = item
                break
            }
        }
        this.actualWish.set(actualWish)
    }

    fun showWishes(v:View) {
        try {
            var wishes = Intent(context as Activity,WishesActivity::class.java)
            wishes.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            (context as Activity).startActivity(wishes)
        } catch (e:Exception) {
            Log.i("Error",e.message)
        }

    }


    fun addWish(v:View) {
        var addWishActivity = Intent(context,AddWishActivity::class.java)
        (context as Activity).startActivity(addWishActivity)
    }
}