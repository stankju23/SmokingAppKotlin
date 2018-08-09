package com.example.stanislavcavajda.bakalarkasmokingapp.Cravings

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class MarkerInfoWindowAdpater : GoogleMap.InfoWindowAdapter {

    var context:Context

    constructor(context:Context) {
        this.context = context
    }

    override fun getInfoContents(marker: Marker?): View {
        var view = (context as Activity).layoutInflater.inflate(R.layout.marker_window_info,null)
        var title = view.findViewById<TextView>(R.id.window_info_title)
        var desc = view.findViewById<TextView>(R.id.window_info_desc)

        if (marker != null) {
            title.setText("${marker.title}")
            desc.setText("${marker.snippet}")
        }

        return view
    }

    override fun getInfoWindow(p0: Marker?): View? {
        return null
    }
}