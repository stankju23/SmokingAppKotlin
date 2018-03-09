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
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.MainProgress.MainProgressViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.MoneySaved.MoneySavedViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager.Wish
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager.WishListViewModel
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.DateConverter
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.JSONParser
import com.example.stanislavcavajda.bakalarkasmokingapp.Model.Date
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.example.stanislavcavajda.bakalarkasmokingapp.databinding.FragmentDashboardBinding
import java.text.NumberFormat
import java.util.Locale
import java.util.Timer
import kotlin.concurrent.timerTask





class DashboardFragment : Fragment() {


    var dateConverter = DateConverter()
    var progress = 0f
    lateinit var dashboardList: DashboardListViewModel
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
        var mainProgress = MainProgressViewModel(date, activity,Data.timeList)


        var image = decodeSampledBitmapFromResource(resources,R.drawable.car,100,70)

        var wish = Wish(1,"Auto",
            "daskjdvadvaksdvaksdvaskdvkassad bduasd asjdbjashdjashdvajsdvjasvdjhasvdjhavdsjhavsdjhavdjadvja",
            10,BitmapDrawable(resources,image),false,activity)

        var wish1 = Wish(1,"Auto",
            "daskjdvadvaksdvaksdvaskdvkassad bduasd asjdbjashdjashdvajsdvjasvdjhasvdjhavdsjhavsdjhavdjadvja",
            12,BitmapDrawable(resources,image),false,activity)
        var wish2 = Wish(1,"Auto",
            "daskjdvadvaksdvaksdvaskdvkassad bduasd asjdbjashdjashdvajsdvjasvdjhasvdjhavdsjhavsdjhavdjadvja",
            13,BitmapDrawable(resources,image),false,activity)
        var wish3 = Wish(1,"Auto4",
            "daskjdvadvaksdvaksdvaskdvkassad bduasd asjdbjashdjashdvajsdvjasvdjhasvdjhavdsjhavsdjhavdjadvja",
            19,BitmapDrawable(resources,image),false,activity)

        var wish4 = Wish(1,"Auto5",
            "daskjdvadvaksdvaksdvaskdvkassad bduasd asjdbjashdjashdvajsdvjasvdjhasvdjhavdsjhavsdjhavdjadvja",
            15,BitmapDrawable(resources,image),false,activity)


        Data.wishList.add(wish)
        Data.wishList.add(wish1)
        Data.wishList.add(wish2)
        Data.wishList.add(wish3)
        Data.wishList.add(wish4)
        Data.wishList.add(wish)
        Data.wishList.add(wish1)
        Data.wishList.add(wish2)
        Data.wishList.add(wish3)
        Data.wishList.add(wish4)
        Data.wishList.add(wish)
        Data.wishList.add(wish1)
        Data.wishList.add(wish2)
        Data.wishList.add(wish3)
        Data.wishList.add(wish4)



        var wishesManager = WishListViewModel(Data.wishList,activity)
        var moneySaved = MoneySavedViewModel(Data.MoneyDashboard.moneySaved,Data.MoneyDashboard.moneySpend)


        dashboardList.list.add(mainProgress as Object)
        dashboardList.list.add(healthProgress as Object)
        dashboardList.list.add(wishesManager as Object)
        dashboardList.list.add(moneySaved as Object)

        var result = ""
        val nf = NumberFormat.getInstance(Locale.US) // Looks like a US format

        updateWishList()

        Timer().scheduleAtFixedRate(timerTask{
            currentTimestamp = dateConverter.getCurrentTimestamp()
            dateTimestamp = dateConverter.convertDateToTimestamp(Data.date)
            date = Date(currentTimestamp - dateTimestamp,Constants.timeConst.twentyOneDays)

            progress = (dateConverter.getCurrentTimestamp() - dateConverter.convertDateToTimestamp(Data.date)).toFloat()/(Constants.timeConst.twentyOneDays).toFloat() * 100f

            (dashboardList.list.get(Constants.viewTypes.MAIN_PROGRESS_VIEW_TYPE) as MainProgressViewModel).setProgress(date)

            Data.timeList.clear()
            Data.timeList.addAll(dateConverter.updateMainProgressDetail(activity,currentTimestamp - dateTimestamp))

            (dashboardList.list.get(Constants.viewTypes.MAIN_PROGRESS_VIEW_TYPE) as MainProgressViewModel).updateDetail(Data.timeList)

            updateHealthProgress(currentTimestamp,dateTimestamp)


            (dashboardList.list.get(Constants.viewTypes.HEALTH_PROGRESS_VIEW_TYPE) as HealthProgressListViewModel).updateAll()
            result = "%.2f".format(actualSaved(currentTimestamp - dateTimestamp,Data.MoneyDashboard.cigarretesPerDay,Data.MoneyDashboard.packagePrice, Data.MoneyDashboard.cigarretesInPackage))

            Data.MoneyDashboard.moneySaved = nf.parse(result).toFloat()/100

            (dashboardList.list.get(Constants.viewTypes.MONEY_SAVED_VIEW_TYPE) as MoneySavedViewModel).updateMoney(
                Data.MoneyDashboard.moneySaved,0.0)

            updateWishList()

            val list = Data.wishList.sortedWith(compareBy(Wish::price))
            Data.wishList = ArrayList(list)
            (dashboardList.list.get(Constants.viewTypes.WISHES_MANAGER_VIEW_TYPE) as WishListViewModel).updateWishList(Data.wishList)


        },0,1000)

        binding.viewModel = dashboardList

        return view
    }

    fun actualSaved(actualTimestamp:Long, cigarretesPerDay: Int, packagePrice:Double,inPackage:Int):Float {
        var result = 0.0f
        var day = 86400
        result = (((packagePrice.toFloat()/ inPackage.toFloat()) * cigarretesPerDay)/ day.toFloat()) * actualTimestamp
        return result
    }

    fun updateWishList() {
        for (i in Data.wishList) {
            i.setWish(i.id,i.title,i.desc,i.price,i.image!!,i.isBought)
        }
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
