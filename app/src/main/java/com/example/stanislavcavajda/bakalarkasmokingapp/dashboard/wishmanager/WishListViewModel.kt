package com.example.stanislavcavajda.bakalarkasmokingapp.dashboard.wishmanager

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
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
    var visibility: ObservableInt = ObservableInt()
    var visibilityEmptyScreen: ObservableInt = ObservableInt()

    lateinit var context: Context

    var itemBinding: ItemBinding<Wish> = ItemBinding.of(BR.viewModel, R.layout.wish_list_item)

    constructor(wishList: ArrayList<Wish>, context: Context) {
        this.wishList.addAll(wishList)
        if (wishList.size == 0) {
            this.visibilityEmptyScreen.set(View.VISIBLE)
        } else {
            this.visibilityEmptyScreen.set(View.GONE)
        }

        updateWish()

        if (actualWish.get() == null) {
            this.visibility.set(View.VISIBLE)
        } else {
            this.visibility.set(View.GONE)
        }

        this.context = context
    }

    fun updateWishList(newWishList: ArrayList<Wish>) {
        this.wishList.clear()
        this.wishList.addAll(newWishList)
        updateWish()

        if (actualWish.get() == null) {
            this.visibility.set(View.VISIBLE)
        } else {
            this.visibility.set(View.GONE)
        }

        if (wishList.size == 0) {
            this.visibilityEmptyScreen.set(View.VISIBLE)
        } else {
            this.visibilityEmptyScreen.set(View.GONE)
        }
    }

    fun updateWish() {
        var actualWish: Wish? = null
        for (item in wishList) {
            if (!item.isBought.get()) {
                actualWish = item
                break
            }
        }
        this.actualWish.set(actualWish)
    }

    fun showWishes(v: View) {
        try {
            var wishes = Intent(context as Activity, WishesActivity::class.java)
            wishes.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            (context as Activity).startActivity(wishes)
        } catch (e: Exception) {
            Log.i("Error", e.message ?: "")
        }
    }

    fun addWish(v: View) {
        var addWishActivity = Intent(context, AddWishActivity::class.java)
        (context as Activity).startActivity(addWishActivity)
    }
}