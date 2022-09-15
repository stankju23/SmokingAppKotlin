package com.example.stanislavcavajda.bakalarkasmokingapp.helper

import android.app.Activity
import android.content.Context
import android.util.Log
import com.example.stanislavcavajda.bakalarkasmokingapp.dashboard.healthlist.HealthProgressViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.missions.Objective
import com.example.stanislavcavajda.bakalarkasmokingapp.model.Date
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.ArrayList
import java.util.UUID

/**
 * Created by stanislavcavajda on 02/03/2018.
 */
object JSONParser {



    fun loadJsonFromAssets(name:String, context:Context): String{

        var json: String? = null
        try {
            val exists = (context as Activity).getAssets().open("$name.json")
            val size = exists.available()
            val buffer = ByteArray(size)
            exists.read(buffer)
            exists.close()
            json = String(buffer, charset("UTF-8"))

        } catch (ex: IOException) {
            ex.printStackTrace()
        }

        return json!!

    }

    fun parseHealthData(data:String,context: Context):ArrayList<HealthProgressViewModel> {
        var healthList = ArrayList<HealthProgressViewModel>()
        var title = ""
        var desc = ""
        val dateConverter = DateConverter()

        try {
            var obj = JSONObject(data)
            var objArray = obj.getJSONArray("stats")
            var arrayItem: JSONObject?= null
            var healthProgres: HealthProgressViewModel

            for (i in 0..objArray.length()) {
                arrayItem = objArray.getJSONObject(i)
                var duration = arrayItem.getDouble("duration").toLong()
                Data.healthProgressTimes.add(duration)
                title = arrayItem.getString("title")
                desc = arrayItem.getString("desc")

                healthProgres = HealthProgressViewModel(i,title,
                    Date(dateConverter.convertDateToTimestamp(Data.date) + duration - dateConverter.getCurrentTimestamp(),duration)
                    ,desc,context)

                healthList.add(healthProgres)
            }


        } catch (e: JSONException) {
            Log.d("Json error", e.message ?: "")
        }


        return healthList

    }


    fun parseObjectives(data: String,context: Context): ArrayList<Objective> {
        var objectiveList = ArrayList<Objective>()

        try {
            var obj = JSONObject(data)
            var objArray = obj.getJSONArray("objectives")

            for (i in 0..objArray.length()) {
                var arrayItem = objArray.getJSONObject(i)
                var id = arrayItem.getInt("id")
                var title = arrayItem.getString("title")
                var desc = arrayItem.getString("desc")

                var titleId = context.resources.getIdentifier(title,"string",context.packageName)
                var descId = context.resources.getIdentifier(desc,"string",context.packageName)

                objectiveList.add(Objective(UUID.randomUUID().toString(),titleId,descId))
            }

        } catch (e:Exception) {

        }

        return objectiveList
    }

    fun parseCurrencies(context: Context):ArrayList<String> {
        var string = loadJsonFromAssets("Currency",context)
        var currencies = ArrayList<String>()

        try {
            var obj = JSONObject(string)
            var currencyArray = obj.getJSONArray("currencies")
            for (i in 0..currencyArray.length()) {
                var currencyObj = currencyArray.getJSONObject(i)
                currencies.add(currencyObj.getString("symbol_native"))
            }
        } catch (e:Exception) {

        }
        return currencies
    }
}