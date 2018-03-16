package com.example.stanislavcavajda.bakalarkasmokingapp.Helper

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager.Wish
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager.WishRealm
import io.realm.Realm

/**
 * Created by stanislavcavajda on 13/03/2018.
 */
object RealmDB {


    fun addWishToDB(wish:Wish) {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        var wishReal = realm.createObject(WishRealm::class.java)
        wishReal.id = wish.id
        wishReal.title = wish.title
        wishReal.desc = wish.desc
        wishReal.price = wish.price
        wishReal.canBuy = wish.canBuy.get()
        wishReal.isBought = wish.isBought.get()
        wishReal.endDate = wish.endDate.get()
        wishReal.progress = wish.progress.get()
        wishReal.image = wish.image.toString()
        realm.commitTransaction()
    }

    fun getWishes(context: Context) {
        var dateConverter = DateConverter()
        var startTimestamp = dateConverter.getCurrentTimestamp()
        val realm = Realm.getDefaultInstance()
        val results = realm.where(WishRealm::class.java).findAll()
        for (item in results) {
            try {
                Log.i("item path", item.image)
                var imageUri:Uri = Uri.parse(item.image)
                var wish = Wish(item.id,item.title,item.desc,item.price,item.isBought,context,imageUri)
                Data.wishList.add(wish)
            } catch (e:Exception) {

            }

        }
        var endTimestamp = dateConverter.getCurrentTimestamp()
        var vysledok = endTimestamp - startTimestamp
        Log.i("Read time ", vysledok.toString())

    }

    fun updateWish(id:String,updatedWish:Wish) {
        val realm = Realm.getDefaultInstance()
        var wish = realm.where(WishRealm::class.java).equalTo("id",id).findFirst()
        realm.beginTransaction()
        var bought = updatedWish.isBought.get()
        wish?.isBought = bought
        wish?.title = updatedWish.title
        wish?.desc = updatedWish.desc
        wish?.price = updatedWish.price
        realm.insertOrUpdate(wish)
        realm.commitTransaction()


    }
}