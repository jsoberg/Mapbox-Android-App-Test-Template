package com.mapbox.android.example.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView

class TestMapActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create a map programmatically and set the initial camera
        val mapView = MapView(this)
        mapView.mapboxMap.setCamera(
            CameraOptions.Builder()
                .center(Point.fromLngLat(-98.0, 39.5))
                .pitch(0.0)
                .zoom(6.0)
                .bearing(0.0)
                .build()
        )
        // Add the map view to the activity (you can also add it to other views as a child)
        setContentView(mapView)
    }
}