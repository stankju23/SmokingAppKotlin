package com.example.stanislavcavajda.bakalarkasmokingapp.Cravings

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.CardView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.jaychang.srv.SimpleCell
import com.jaychang.srv.SimpleViewHolder



/**
 * Created by stanislavcavajda on 12/05/2018.
 */

class CravingCell(item: Craving) : SimpleCell<Craving, CravingCell.ViewHolder>(item) {



    override fun onCreateViewHolder(p0: ViewGroup, cellView: View): ViewHolder {
        return ViewHolder(cellView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int, context: Context, o: Any?) {
        viewHolder.time.setText(item.time)
        viewHolder.city.setText(item.city)
        if (position % 2 == 1) {
            viewHolder.card.setCardBackgroundColor(Color.parseColor("#EFEFEF"))
        } else {
            viewHolder.card.setCardBackgroundColor(context.resources.getColor(R.color.white))
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.craving_item
    }


    inner class ViewHolder:SimpleViewHolder {

        var time: TextView
        var city: TextView
        var card: CardView

        constructor(itemView:View) : super(itemView) {
            time = itemView.findViewById(R.id.time)
            city = itemView.findViewById(R.id.city)
            card = itemView.findViewById(R.id.card)


        }
    }
}