package com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard


import android.app.Fragment
import android.content.res.Resources
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.HealthList.HealthProgressListViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager.Wish
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager.WishListViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.DateConverter
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.JSONParser
import com.example.stanislavcavajda.bakalarkasmokingapp.Model.Date
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.FragmentDashboardBinding
import java.util.Timer
import kotlin.concurrent.timerTask





class DashboardFragment : Fragment() {


    var dateConverter = DateConverter()
    var progress = 0f
    lateinit var dashboardList:DashboardListViewModel
    lateinit var date: Date
    var currentTimestamp: Long = 0L
    var dateTimestamp: Long = 0L

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var binding:FragmentDashboardBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)


        var view = binding.root

        date = Date(dateConverter.getCurrentTimestamp() - dateConverter.convertDateToTimestamp(Data.date),Constants.timeConst.twentyOneDays)

        if(Data.healthProgressViewList.isEmpty()) {
            prepareHealthProgressList()
        }

        dashboardList = DashboardListViewModel(ArrayList(), activity.applicationContext)

        var healthProgress = HealthProgressListViewModel(Data.healthProgressViewList, activity)
        var mainProgress = MainProgressViewModel(date,activity)


        var image = decodeSampledBitmapFromResource(resources,R.drawable.car,100,70)

        var wish = Wish(1,"Auto",
            "daskjdvadvaksdvaksdvaskdvkassad bduasd asjdbjashdjashdvajsdvjasvdjhasvdjhavdsjhavsdjhavdjadvja",
            1000,BitmapDrawable(resources,image),false,false)

        Data.wishList.add(wish)
        Data.wishList.add(wish)
        Data.wishList.add(wish)
        Data.wishList.add(wish)
        Data.wishList.add(wish)
        Data.wishList.add(wish)
        Data.wishList.add(wish)
        Data.wishList.add(wish)
        Data.wishList.add(wish)
        Data.wishList.add(wish)
        Data.wishList.add(wish)
        Data.wishList.add(wish)


        var wishesManager = WishListViewModel(Data.wishList,activity)


        dashboardList.list.add(mainProgress as Object)
        dashboardList.list.add(healthProgress as Object)
        dashboardList.list.add(wishesManager as Object)



        Timer().scheduleAtFixedRate(timerTask{
            currentTimestamp = dateConverter.getCurrentTimestamp()
            dateTimestamp = dateConverter.convertDateToTimestamp(Data.date)
            date = Date(currentTimestamp - dateTimestamp,Constants.timeConst.twentyOneDays)

            progress = (dateConverter.getCurrentTimestamp() - dateConverter.convertDateToTimestamp(Data.date)).toFloat()/(Constants.timeConst.twentyOneDays).toFloat() * 100f

            (dashboardList.list.get(0) as MainProgressViewModel).setProgress(date)
            updateHealthProgress(currentTimestamp,dateTimestamp)
            (dashboardList.list.get(1) as HealthProgressListViewModel).updateAll()

        },0,1000)

        binding.viewModel = dashboardList

        return view
    }



    fun prepareHealthProgressList() {
        Data.healthProgressViewList.addAll(JSONParser.parseHealthData(JSONParser.loadJsonFromAssets("HealthData",activity),activity))
    }


    fun updateHealthProgress(currentTimestamp: Long, dateTimestamp: Long) {
        var index = 0
        for (item in Data.healthProgressTimes) {
            date = Date(dateTimestamp + item - currentTimestamp,item)
            Data.healthProgressViewList[index].date.set(date)
            index++
        }
    }


    fun decodeSampledBitmapFromResource(res: Resources, resId: Int,
                                        reqWidth: Int, reqHeight: Int): Bitmap {

        // First decode with inJustDecodeBounds=true to check dimensions
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(res, resId, options)

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeResource(res, resId, options)
    }

    fun calculateInSampleSize(
        options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        // Raw height and width of image
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {

            val halfHeight = height / 2
            val halfWidth = width / 2

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while (halfHeight / inSampleSize > reqHeight && halfWidth / inSampleSize > reqWidth) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }

}
