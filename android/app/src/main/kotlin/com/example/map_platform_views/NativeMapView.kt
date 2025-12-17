package com.example.map_platform_views

import android.content.Context
import android.view.View
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.platform.PlatformView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Marker

class NativeMapView(
    context: Context,
    messenger: BinaryMessenger,
    id: Int,
    creationParams: Map<String, Any>?
) : PlatformView, OnMapReadyCallback {

    private val mapView: MapView = MapView(context)
    private var googleMap: GoogleMap? = null
    private val markers = mutableListOf<Marker>()
    
    private val initialLat: Double
    private val initialLng: Double
    private val initialZoom: Float

    init {
        // Extract initial parameters or use Cairo defaults
        initialLat = (creationParams?.get(MapConstants.INITIAL_LAT_KEY) as? Double) 
            ?: MapConstants.CAIRO_LATITUDE
        initialLng = (creationParams?.get(MapConstants.INITIAL_LNG_KEY) as? Double) 
            ?: MapConstants.CAIRO_LONGITUDE
        initialZoom = ((creationParams?.get(MapConstants.INITIAL_ZOOM_KEY) as? Double)?.toFloat()) 
            ?: MapConstants.DEFAULT_ZOOM
        
        mapView.onCreate(null)
        mapView.getMapAsync(this)
    }

    override fun getView(): View = mapView

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        
        // Set initial camera position to Cairo, Egypt
        val initialPos = LatLng(initialLat, initialLng)
        googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(initialPos, initialZoom))
        
        // Enable UI controls
        googleMap?.uiSettings?.isZoomControlsEnabled = true
        googleMap?.uiSettings?.isCompassEnabled = true
        googleMap?.uiSettings?.isMyLocationButtonEnabled = true
    }

    fun addMarker(lat: Double, lng: Double, title: String?, snippet: String?) {
        googleMap?.let { map ->
            val position = LatLng(lat, lng)
            val markerOptions = MarkerOptions()
                .position(position)
                .title(title)
                .snippet(snippet)
            
            val marker = map.addMarker(markerOptions)
            marker?.let { markers.add(it) }
        }
    }

    fun moveCamera(lat: Double, lng: Double, zoom: Float) {
        googleMap?.let { map ->
            val position = LatLng(lat, lng)
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(position, zoom))
        }
    }

    fun clearMarkers() {
        markers.forEach { it.remove() }
        markers.clear()
    }

    override fun dispose() {
        mapView.onDestroy()
    }
}