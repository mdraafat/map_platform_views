class MapConstants {
  // Channel Names
  static const String methodChannel = 'native_maps_channel';
  static const String viewType = 'native_map_view';
  
  static const String addMarkerMethod = 'addMarker';
  static const String moveCameraMethod = 'moveCamera';
  static const String clearMarkersMethod = 'clearMarkers';
  
  static const String latitudeKey = 'latitude';
  static const String longitudeKey = 'longitude';
  static const String titleKey = 'title';
  static const String snippetKey = 'snippet';
  static const String zoomKey = 'zoom';
  static const String initialLatKey = 'initialLat';
  static const String initialLngKey = 'initialLng';
  static const String initialZoomKey = 'initialZoom';
  
  static const double cairoLatitude = 30.0444;
  static const double cairoLongitude = 31.2357;
  static const double defaultZoom = 12.0;
  
  static const double alexLatitude = 31.2001;
  static const double alexLongitude = 29.9187;
  
  static const String addMarkerError = 'Error adding marker';
  static const String moveCameraError = 'Error moving camera';
  static const String clearMarkersError = 'Error clearing markers';
}