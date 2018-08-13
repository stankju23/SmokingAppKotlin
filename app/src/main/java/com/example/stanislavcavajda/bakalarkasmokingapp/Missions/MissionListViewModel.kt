package com.example.stanislavcavajda.bakalarkasmokingapp.Missions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.stanislavcavajda.bakalarkasmokingapp.BR
import com.example.stanislavcavajda.bakalarkasmokingapp.Dashboard.WishManager.OnWishClick
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Animate
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Data
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Created by stanislavcavajda on 21/03/2018.
 */
class MissionListViewModel {

    var missionList: ObservableArrayList<Mission> = ObservableArrayList()

    var listener = object : OnWishClick{
        override fun onWishClick(v: View) {
            var recyclerView: RecyclerView = (context as Activity).findViewById(R.id.missions_recycler_view)
            var clickedPosition = recyclerView.getChildAdapterPosition(v)
            Data.actualClickedMission = clickedPosition
            var missionInfo = Intent(context, MissionInfoActivity::class.java)
            if (Data.missionList[clickedPosition].available.get()) {
                (context as Activity).startActivity(missionInfo)
            } else {
                Animate.shakeAnim(v,context)
            }
        }
    }

    var itemBinding : ItemBinding<Mission> = ItemBinding.of(BR.mission, R.layout.mission_list_item)
    var context:Context



    constructor(missions:ArrayList<Mission>,context: Context) {
        itemBinding.bindExtra(BR.listener,listener)
        missionList.addAll(missions)
        this.context = context
    }

    fun updateList(missions: ArrayList<Mission>){
        var index = 0
        for (item in missionList) {
            item.setMission(missions[index])
            index++
        }
    }
}