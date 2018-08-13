package com.example.stanislavcavajda.bakalarkasmokingapp.Main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.stanislavcavajda.bakalarkasmokingapp.Helper.Constants
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import kotlinx.android.synthetic.main.achievment_item.view.*

class AchievmentAdapter(var achievmentList:ArrayList<Achievment>,var context: Context) : RecyclerView.Adapter<AchievmentAdapter.AchievmentViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AchievmentViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.achievment_item, parent, false)
        return AchievmentViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return achievmentList.size
    }

    override fun onBindViewHolder(holder: AchievmentViewHolder, position: Int) {
        holder.achievmentImage.setImageDrawable(achievmentList[position].image)
        if (!achievmentList[position].isComplete) {
            holder.achievmentImage.alpha = 0.5f
        } else {
            holder.achievmentImage.alpha = 1f
        }

        holder.achievmentImage.setOnClickListener {
            if (achievmentList[position].isComplete) {
                var achievmentActivity = Intent(context, AchievmentActivity::class.java)
                achievmentActivity.putExtra(Constants.extras.EXTRA_ITEM_ID, position)

                if (achievmentList[position].endTimestamp == 0L) {
                    achievmentActivity.putExtra("mission", true)
                }else {
                    achievmentActivity.putExtra("mission", false)
                }
                (context as Activity).startActivity(achievmentActivity)
            }
        }
    }

    inner class AchievmentViewHolder: RecyclerView.ViewHolder {

        var achievmentImage:ImageView

        constructor(view:View) : super(view) {
            this.achievmentImage = view.achievment_image
        }
    }
}