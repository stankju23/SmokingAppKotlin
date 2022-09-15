package com.example.stanislavcavajda.bakalarkasmokingapp.realmdatabase

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.stanislavcavajda.bakalarkasmokingapp.cravings.Craving
import com.example.stanislavcavajda.bakalarkasmokingapp.cravings.CravingHeader
import com.example.stanislavcavajda.bakalarkasmokingapp.dashboard.wishmanager.Wish
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.helper.DateConverter
import com.example.stanislavcavajda.bakalarkasmokingapp.journal.Journal
import com.example.stanislavcavajda.bakalarkasmokingapp.koloda.KolodaItem
import com.example.stanislavcavajda.bakalarkasmokingapp.missions.Activity
import com.example.stanislavcavajda.bakalarkasmokingapp.missions.Mission
import com.example.stanislavcavajda.bakalarkasmokingapp.missions.Objective
import com.example.stanislavcavajda.bakalarkasmokingapp.model.Date
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
                var mission = Mission(item.id,item.name,item.date,Date(0,0),item.locked,item.available,activities,item.done,context)
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
        realmCraving.isHeader = false
        realmCraving.blacBG = craving.blackBG
        realmCraving.timeStamp = craving.timeStamp
        realm.commitTransaction()
    }

    fun saveHeader(header:CravingHeader) {
        realm.beginTransaction()
        var realmCraving = realm.createObject(CravingRealm::class.java)
        realmCraving.id = header.id
        realmCraving.date = header.date
        realmCraving.isHeader = true
        realm.commitTransaction()
    }

    fun getCravings(context: Context)
    {


        val result = realm.where(CravingRealm::class.java).findAll()

        for (item in result) {
            try
            {
                if (item.isHeader)
                {
                    var cravingHeader = CravingHeader(item.id,item.date)
                    Data.cravings.add(cravingHeader)
                } else
                {
                    var craving = Craving(item.id,item.time,item.date,item.latitude,item.longitude,context,item.blacBG,item.timeStamp)
                    Data.cravings.add(craving)
                }

            } catch (e:Exception) {
                Log.i("Cravings" ,"Can't load")
            }
        }

    }

    fun addCard(card:KolodaItem) {
        realm.beginTransaction()
        var realmCard = realm.createObject(CardRealm::class.java)
        realmCard.id = card.id
        realmCard.title = card.title
        realmCard.desc = card.desc
        realmCard.category = card.category
        realmCard.popularity = card.popularity
        realm.commitTransaction()
    }

    fun updateCard(card:KolodaItem) {

        var realmCard = realm.where(CardRealm::class.java).equalTo("id",card.id).findFirst()

        if (realmCard != null) {
            realm.beginTransaction()
            realmCard.popularity = card.popularity
            realm.insertOrUpdate(realmCard)
            realm.commitTransaction()
        }
    }

    fun loadCards(context:Context) {
        val result = realm.where(CardRealm::class.java).findAll()

        for (item in result) {
            try {
                var kolodaItem = KolodaItem(item.id,item.category,item.title,item.desc,context)
                Data.cravingsCardList.add(kolodaItem)
            } catch (e:Exception) {
                Log.e("Load card error : ", e.message ?: "")
            }
        }
    }

    fun addJournal(journal:Journal) {
        realm.beginTransaction()
        var realmJournal = realm.createObject(JournalRealm::class.java)
        realmJournal.id = journal.id
        realmJournal.title = journal.title.get()!!
        realmJournal.desc = journal.desc.get()!!
        realmJournal.timestamp = journal.timestamp
        realm.commitTransaction()
    }

    fun updateJournal(journal:Journal) {
        var realmJournal = realm.where(JournalRealm::class.java).equalTo("id",journal.id).findFirst()
        if (realmJournal != null) {
            realm.beginTransaction()
            realmJournal.title = journal.title.get()!!
            realmJournal.desc = journal.desc.get()!!
            realm.commitTransaction()
        }
    }

    fun getJournalCard(context:Context) {
        val result = realm.where(JournalRealm::class.java).findAll()

        for (item in result) {
            try {
                var journal = Journal(item.id,item.title,item.desc,item.timestamp,context)
                Data.journalCardSList.add(journal)
            } catch (e:Exception) {

            }
        }
    }
}