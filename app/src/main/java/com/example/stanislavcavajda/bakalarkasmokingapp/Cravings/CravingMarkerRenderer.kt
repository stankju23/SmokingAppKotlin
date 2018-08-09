package com.example.stanislavcavajda.bakalarkasmokingapp.Cravings

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v7.widget.AppCompatDrawableManager
import com.example.stanislavcavajda.bakalarkasmokingapp.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer



class CravingMarkerRenderer: DefaultClusterRenderer<Craving> {

    var context:Context


    constructor(mClusterManager: ClusterManager<Craving>,context: Context,mMap:GoogleMap) : super(context,mMap,mClusterManager) {
        this.context = context
    }

    override fun shouldRenderAsCluster(cluster: Cluster<Craving>?): Boolean {
        if (cluster != null) {
            return cluster.size > 1
        }
        return true
    }

    @SuppressLint("RestrictedApi")
    override fun onBeforeClusterItemRendered(item: Craving?, markerOptions: MarkerOptions?) {

        var drawable = AppCompatDrawableManager.get()
            .getDrawable(context, R.drawable.ic_marker)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = DrawableCompat.wrap(drawable).mutate()
        }
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth,
            drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
        drawable.draw(canvas)
        markerOptions?.icon(BitmapDescriptorFactory.fromBitmap(bitmap))
        super.onBeforeClusterItemRendered(item, markerOptions)
    }

}