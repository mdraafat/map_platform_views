package com.example.map_platform_views

import android.content.Context
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.StandardMessageCodec
import io.flutter.plugin.platform.PlatformView
import io.flutter.plugin.platform.PlatformViewFactory

class NativeMapViewFactory(private val messenger: BinaryMessenger) : 
    PlatformViewFactory(StandardMessageCodec.INSTANCE) {
    
    private var mapView: NativeMapView? = null

    override fun create(context: Context, viewId: Int, args: Any?): PlatformView {
        val creationParams = args as? Map<String, Any>
        mapView = NativeMapView(context, messenger, viewId, creationParams)
        return mapView!!
    }

    fun addMarker(lat: Double, lng: Double, title: String?, snippet: String?) {
        mapView?.addMarker(lat, lng, title, snippet)
    }

    fun moveCamera(lat: Double, lng: Double, zoom: Float) {
        mapView?.moveCamera(lat, lng, zoom)
    }

    fun clearMarkers() {
        mapView?.clearMarkers()
    }
}