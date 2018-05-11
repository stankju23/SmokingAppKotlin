package com.example.stanislavcavajda.bakalarkasmokingapp.Koloda

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import kotlinx.android.synthetic.main.koloda_card.view.*

/**
 * Created by stanislavcavajda on 09/05/2018.
 */
class KolodaAdapter(val context:Context, list:ArrayList<KolodaItem>) : BaseAdapter() {

    var list:ArrayList<KolodaItem> = ArrayList()

    init {
        if (list!= null) {
            this.list.addAll(list)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val holder:KolodaItemViewHolder
        var view = convertView

        if (view == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.koloda_card,parent,false)
            holder = KolodaItemViewHolder(view)
            view?.tag = holder
        } else {
            holder = view?.tag as KolodaItemViewHolder
        }

        holder.bindData(context,getItem(position) as KolodaItem)


        return view
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

    fun setData(data: List<KolodaItem>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    class KolodaItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var image = view.category_image
        var title = view.card_title
        var desc = view.card_desc

        internal fun bindData(context: Context,item:KolodaItem) {
            image.setImageDrawable(item.image)
            title.setText(item.title)
            desc.setText(item.desc)
        }

    }

}