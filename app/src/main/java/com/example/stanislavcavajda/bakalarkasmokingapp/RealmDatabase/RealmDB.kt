package com.example.stanislavcavajda.bakalarkasmokingapp.RealmDatabase

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.stanislavcavajda.bakalarkasmokingapp.Cravings.Craving
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager.Wish
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.DateConverter
import com.example.stanislavcavajda.bakalarkasmokingapp.Missions.Activity
import com.example.stanislavcavajda.bakalarkasmokingapp.Missions.Mission
import com.example.stanislavcavajda.bakalarkasmokingapp.Missions.Objective
import com.example.stanislavcavajda.bakalarkasmokingapp.Model.Date
import io.realm.Realm
import io.realm.RealmList
import java.util.*

/**
 * Created by stanislavcavajda on 13/03/2018.
 */
object RealmDB {

    val realm = Realm.getDefaultInstance()

    fun addWishToDB(wish:Wish) {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        var wishReal = realm.createObject(WishRealm::class.java)
        wishReal.id = wish.id
        wishReal.title = wish.title.get()!!
        wishReal.desc = wish.desc.get()!!
        wishReal.price = wish.price
        wishReal.canBuy = wish.canBuy.get()
        wishReal.isBought = wish.isBought.get()
        wishReal.endDate = wish.endDate.get()!!
        wishReal.progress = wish.progress.get()
        wishReal.image = wish.image.toString()
        realm.commitTransaction()
    }

//    fun addObjective(objective:Objective) {
//        realm.beginTransaction()
//        var objectiveRealm = realm.createObject(ObjectiveRealm::class.java)
//        objectiveRealm.id = objective.id
//        objectiveRealm.title = objective.title
//        objectiveRealm.desc = objective.desc
//        realm.commitTransaction()
//    }




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
        wish?.title = updatedWish.title.get()!!
        wish?.desc = updatedWish.desc.get()!!
        wish?.price = updatedWish.price
        realm.insertOrUpdate(wish)
        realm.commitTransaction()

    }


    fun deleteWish(wish: Wish) {
        val realm = Realm.getDefaultInstance()
        var deletedWish = wish
        var deleteWish = realm.where(WishRealm::class.java).equalTo("id",deletedWish.id).findAll()
        realm.beginTransaction()
        deleteWish.deleteAllFromRealm()
        realm.commitTransaction()
    }


    //Mission management
    fun addMission(mission:Mission) {

        realm.beginTransaction()
        var realmMission = realm.createObject(MissionRealm::class.java)
        realmMission.id = mission.id
        realmMission.name = mission.name
        realmMission.locked = mission.locked.get()
        realmMission.available = mission.available.get()
        realmMission.date = mission.date
        realmMission.done = mission.done

        var realmActivities:RealmList<ActivityRealm> = RealmList()

        for(item in mission.activities!!) {
            var realmObjective = ObjectiveRealm()
            var activityRealm = ActivityRealm()
            realmObjective.id = item.objective?.id!!
            realmObjective.title = item.objective?.title!!
            realmObjective.desc = item.objective?.desc!!
            activityRealm.id = item.id
            activityRealm.isDone = item.isDone.get()
            activityRealm.objective = realmObjective
            realmActivities.add(activityRealm)
        }

        realmMission.activities?.addAll(realmActivities)

        realm.commitTransaction()
    }

    fun updateMission(updateActivty: Activity) {
        var activity = realm.where(ActivityRealm::class.java).equalTo("id",updateActivty.id).findFirst()
        realm.beginTransaction()
        activity?.isDone = updateActivty.isDone.get()
        realm.insertOrUpdate(activity)
        realm.commitTransaction()
    }

    fun getMissions(context: Context) {
        var dateConverter = DateConverter()
        var startTimestamp = dateConverter.getCurrentTimestamp()


        val results = realm.where(MissionRealm::class.java).findAll()
        for (item in results) {
            try {
                var activities = ArrayList<Activity>()
                for (item in item.activities!!) {
                    var objective =  Objective(item.objective?.id!!,item.objective?.title!!,item.objective?.desc!!)
                    var activity = Activity(item.isDone,objective,item.id)
                    activities.add(activity)
                }
                var mission = Mission(item.id,item.name,item.date,Date(0,0),item.locked,item.available,activities,item.done)
               Data.missionList.add(mission)
            } catch (e:Exception) {

            }

        }
        var endTimestamp = dateConverter.getCurrentTimestamp()
        var vysledok = endTimestamp - startTimestamp
        Log.i("Read time ", vysledok.toString())

    }

    fun saveCraving(craving:Craving) {
        realm.beginTransaction()
        var realmCraving = realm.createObject(CravingRealm::class.java)
        realmCraving.id = craving.id
        realmCraving.time = craving.time
        realmCraving.date = craving.date
        realmCraving.latitude = craving.latitude
        realmCraving.longitude = craving.longitude
        realm.commitTransaction()
    }

    fun getCravings(context: Context) {

        val result = realm.where(CravingRealm::class.java).findAll()

        for (item in result) {
            try {
                var craving = Craving(item.id,item.time,item.date,item.latitude,item.longitude,context)
                Data.cravings.add(craving)
            } catch (e:Exception) {
                Log.i("Cravings" ,"Can't load")
            }
        }

    }
}