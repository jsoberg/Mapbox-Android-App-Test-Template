package com.mapbox.android.example.app.animation

import android.util.Log
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.mapbox.maps.CoordinateBounds
import com.mapbox.maps.EdgeInsets
import com.mapbox.maps.MapboxMap
import com.mapbox.maps.plugin.animation.MapAnimationOptions
import com.mapbox.maps.plugin.animation.flyTo

internal fun MapboxMap.initiateFlyTo(
    bounds: CoordinateBounds,
    flyToTimeMillis: Long = 1000L,
    bearing: Double = 0.0,
    boundsPadding: EdgeInsets = EdgeInsets(0.0, 0.0, 0.0, 0.0),
) {
    val cameraOptions = cameraForCoordinateBounds(
        bearing = bearing,
        bounds = bounds,
        boundsPadding = boundsPadding,
        pitch = 0.0,
    )
    Log.d("InitiateFlyToAnimation", "FlyTo options: $cameraOptions")
    flyTo(
        cameraOptions = cameraOptions,
        animationOptions = MapAnimationOptions.mapAnimationOptions {
            duration(flyToTimeMillis)
            interpolator(FastOutSlowInInterpolator())
        },
    )
}