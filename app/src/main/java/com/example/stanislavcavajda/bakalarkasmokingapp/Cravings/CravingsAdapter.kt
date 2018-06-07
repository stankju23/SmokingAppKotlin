package com.example.stanislavcavajda.bakalarkasmokingapp.Cravings

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.brandongogetap.stickyheaders.exposed.StickyHeaderHandler
import com.example.stanislavcavajda.bakalarkasmokingapp.R

class CravingsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>,StickyHeaderHandler {


    var list:ArrayList<CravingItem>
    var context:Context

    constructor(list: ArrayList<CravingItem>,context: Context) {
        this.list = list
        this.context = context
    }

    override fun getAdapterData(): MutableList<*> {
        return this.list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.craving_header, parent, false)
            return HeaderViewHolder(view)
        } else {
            var view = LayoutInflater.from(parent.context).inflate(R.layout.craving_item, parent, false)
            return CravingViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return this.list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CravingViewHolder) {
            if (position % 2 == 1) {
                holder.card.setCardBackgroundColor(context.resources.getColor(R.color.item_grey))
            } else {
                holder.card.setCardBackgroundColor(context.resources.getColor(R.color.white))
            }
            holder.time.text = (this.list[position] as Craving).time
            holder.city.text = (this.list[position] as Craving).city
        } else if (holder is HeaderViewHolder) {
            holder.date.text = (this.list[position] as CravingHeader).date
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (this.list[position] is CravingHeader) {
            return 0
        } else {
            return 1
        }
    }


    private class CravingViewHolder : RecyclerView.ViewHolder {

        lateinit var time: TextView
        lateinit var city: TextView
        lateinit var card: CardView

        constructor(itemView: View?) : super(itemView) {
            if (itemView != null) {
                this.time = itemView.findViewById(R.id.time)
                this.city = itemView.findViewById(R.id.city)
                this.card = itemView.findViewById(R.id.card)
            }
        }
    }


    private class HeaderViewHolder : RecyclerView.ViewHolder {

        lateinit var date: TextView

        constructor(itemView: View?) : super(itemView) {
            if (itemView != null) {
                this.date = itemView.findViewById(R.id.craving_date)
            }
        }
    }
}