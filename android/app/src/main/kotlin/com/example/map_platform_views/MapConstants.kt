package com.example.map_platform_views

object MapConstants {
    // Channel Names
    const val METHOD_CHANNEL = "native_maps_channel"
    const val VIEW_TYPE = "native_map_view"
    
    // Method Names
    const val ADD_MARKER_METHOD = "addMarker"
    const val MOVE_CAMERA_METHOD = "moveCamera"
    const val CLEAR_MARKERS_METHOD = "clearMarkers"
    
    // Parameter Keys
    const val LATITUDE_KEY = "latitude"
    const val LONGITUDE_KEY = "longitude"
    const val TITLE_KEY = "title"
    const val SNIPPET_KEY = "snippet"
    const val ZOOM_KEY = "zoom"
    const val INITIAL_LAT_KEY = "initialLat"
    const val INITIAL_LNG_KEY = "initialLng"
    const val INITIAL_ZOOM_KEY = "initialZoom"
    
    // Cairo, Egypt - Default Location
    const val CAIRO_LATITUDE = 30.0444
    const val CAIRO_LONGITUDE = 31.2357
    const val DEFAULT_ZOOM = 12f
    
    // Error Codes
    const val ERROR_INVALID_ARGS = "INVALID_ARGS"
    const val ERROR_INVALID_COORDS = "Invalid coordinates"
    const val ERROR_INVALID_PARAMS = "Invalid arguments"
}