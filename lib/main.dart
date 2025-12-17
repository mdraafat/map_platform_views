import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'map_constants.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Native Maps Demo',
      theme: ThemeData(primarySwatch: Colors.blue),
      home: const MapScreen(),
    );
  }
}

class MapScreen extends StatefulWidget {
  const MapScreen({super.key});

  @override
  State<MapScreen> createState() => _MapScreenState();
}

class _MapScreenState extends State<MapScreen> {
  static const platform = MethodChannel(MapConstants.methodChannel);
  MapViewController? _controller;

  void _addMarker() async {
    try {
      await platform.invokeMethod(MapConstants.addMarkerMethod, {
        MapConstants.latitudeKey: MapConstants.cairoLatitude,
        MapConstants.longitudeKey: MapConstants.cairoLongitude,
        MapConstants.titleKey: 'Cairo',
        MapConstants.snippetKey: 'Capital of Egypt'
      });
    } catch (e) {
      debugPrint('${MapConstants.addMarkerError}: $e');
    }
  }

  void _moveToAlexandria() async {
    try {
      await platform.invokeMethod(MapConstants.moveCameraMethod, {
        MapConstants.latitudeKey: MapConstants.alexLatitude,
        MapConstants.longitudeKey: MapConstants.alexLongitude,
        MapConstants.zoomKey: MapConstants.defaultZoom
      });
    } catch (e) {
      debugPrint('${MapConstants.moveCameraError}: $e');
    }
  }

  void _clearMarkers() async {
    try {
      await platform.invokeMethod(MapConstants.clearMarkersMethod);
    } catch (e) {
      debugPrint('${MapConstants.clearMarkersError}: $e');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Native Google Maps - Egypt'),
      ),
      body: Column(
        children: [
          Expanded(
            child: AndroidView(
              viewType: MapConstants.viewType,
              onPlatformViewCreated: (id) {
                _controller = MapViewController._(id);
              },
              creationParams: {
                MapConstants.initialLatKey: MapConstants.cairoLatitude,
                MapConstants.initialLngKey: MapConstants.cairoLongitude,
                MapConstants.initialZoomKey: MapConstants.defaultZoom,
              },
              creationParamsCodec: const StandardMessageCodec(),
            ),
          ),
          Container(
            padding: const EdgeInsets.all(16),
            color: Colors.white,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                ElevatedButton(
                  onPressed: _addMarker,
                  child: const Text('Add Cairo'),
                ),
                ElevatedButton(
                  onPressed: _moveToAlexandria,
                  child: const Text('Alexandria'),
                ),
                ElevatedButton(
                  onPressed: _clearMarkers,
                  child: const Text('Clear'),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}

class MapViewController {
  MapViewController._(this.id);
  final int id;
}