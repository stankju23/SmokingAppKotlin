package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.BaseObservable
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.stanislavcavajda.bakalarkasmokingapp.BR
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Created by stanislavcavajda on 04/03/2018.
 */
class WishListViewModel: BaseObservable {

    var wishList: ObservableArrayList<Wish> = ObservableArrayList()
    var actualWish: ObservableField<Wish> = ObservableField()

    lateinit var context: Context

    var listener = object : OnWishClick {

        override fun onWishClick(v: View) {

            if (Data.swipeCanClick) {
                var recyclerView: RecyclerView = (context as Activity).findViewById(R.id.wishes_recycler)
                var clickedPosition = recyclerView.getChildAdapterPosition(v)
                if (!Data.wishList[clickedPosition].isBought.get()) {
                    var editWish = Intent(context, EditWishActivity::class.java)
                    editWish.putExtra(Constants.extras.EXTRA_ITEM_ID, clickedPosition)
                    (context as Activity).startActivity(editWish)
                }
            }

        }
    }

    var itemBinding : ItemBinding<Wish> = ItemBinding.of(BR.viewModel, R.layout.wish_list_item)

    constructor(wishList: ArrayList<Wish>, context: Context) {

        itemBinding.bindExtra(BR.listener,listener)
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
        var wishes = Intent(context,WishesActivity::class.java)
        (context as Activity).startActivity(wishes)
    }


    fun addWish(v:View) {
        var addWishActivity = Intent(context,AddWishActivity::class.java)
        (context as Activity).startActivity(addWishActivity)
    }
}