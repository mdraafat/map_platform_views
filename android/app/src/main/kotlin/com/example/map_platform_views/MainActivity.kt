package com.example.map_platform_views

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {
    private var mapViewFactory: NativeMapViewFactory? = null

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        
        mapViewFactory = NativeMapViewFactory(flutterEngine.dartExecutor.binaryMessenger)
        
        flutterEngine
            .platformViewsController
            .registry
            .registerViewFactory(MapConstants.VIEW_TYPE, mapViewFactory!!)
        
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, MapConstants.METHOD_CHANNEL)
            .setMethodCallHandler { call, result ->
                when (call.method) {
                    MapConstants.ADD_MARKER_METHOD -> {
                        val lat = call.argument<Double>(MapConstants.LATITUDE_KEY)
                        val lng = call.argument<Double>(MapConstants.LONGITUDE_KEY)
                        val title = call.argument<String>(MapConstants.TITLE_KEY)
                        val snippet = call.argument<String>(MapConstants.SNIPPET_KEY)
                        
                        if (lat != null && lng != null) {
                            mapViewFactory?.addMarker(lat, lng, title, snippet)
                            result.success(null)
                        } else {
                            result.error(
                                MapConstants.ERROR_INVALID_ARGS,
                                MapConstants.ERROR_INVALID_COORDS,
                                null
                            )
                        }
                    }
                    MapConstants.MOVE_CAMERA_METHOD -> {
                        val lat = call.argument<Double>(MapConstants.LATITUDE_KEY)
                        val lng = call.argument<Double>(MapConstants.LONGITUDE_KEY)
                        val zoom = call.argument<Double>(MapConstants.ZOOM_KEY)?.toFloat()
                        
                        if (lat != null && lng != null && zoom != null) {
                            mapViewFactory?.moveCamera(lat, lng, zoom)
                            result.success(null)
                        } else {
                            result.error(
                                MapConstants.ERROR_INVALID_ARGS,
                                MapConstants.ERROR_INVALID_PARAMS,
                                null
                            )
                        }
                    }
                    MapConstants.CLEAR_MARKERS_METHOD -> {
                        mapViewFactory?.clearMarkers()
                        result.success(null)
                    }
                    else -> result.notImplemented()
                }
            }
    }
}

