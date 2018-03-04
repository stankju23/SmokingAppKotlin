package com.example.stanislavcavajda.bakalarkasmokingapp.Helper

import android.app.Activity
import android.content.Context
import android.util.Log
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.HealthList.HealthProgressViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.Model.Date
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

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
                var duration = arrayItem?.getDouble("duration")?.toLong()!!
                Data.healthProgressTimes.add(duration)
                title = arrayItem?.getString("title")
                desc = arrayItem?.getString("desc")

                healthProgres = HealthProgressViewModel(i,title,
                    Date(dateConverter.convertDateToTimestamp(Data.date) + duration - dateConverter.getCurrentTimestamp(),duration)
                    ,desc,context)

                healthList.add(healthProgres)
            }


        } catch (e: JSONException) {
            Log.d("Json error", e.message)
        }


        return healthList

    }
}