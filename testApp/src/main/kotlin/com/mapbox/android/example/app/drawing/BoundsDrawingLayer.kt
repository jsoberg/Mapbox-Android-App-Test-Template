package com.mapbox.android.example.app.drawing

import com.mapbox.geojson.Feature
import com.mapbox.geojson.FeatureCollection
import com.mapbox.geojson.LineString
import com.mapbox.geojson.Point
import com.mapbox.maps.CoordinateBounds
import com.mapbox.maps.Style
import com.mapbox.maps.extension.style.layers.addLayer
import com.mapbox.maps.extension.style.layers.generated.LineLayer
import com.mapbox.maps.extension.style.layers.getLayer
import com.mapbox.maps.extension.style.layers.properties.generated.LineCap
import com.mapbox.maps.extension.style.layers.properties.generated.LineJoin
import com.mapbox.maps.extension.style.sources.addSource
import com.mapbox.maps.extension.style.sources.generated.GeoJsonSource
import com.mapbox.maps.extension.style.sources.generated.geoJsonSource
import com.mapbox.maps.extension.style.sources.getSourceAs
import java.util.UUID

class BoundsDrawingLayer(style: Style) {

    private val dataSource: GeoJsonSource = createGeoJsonSource(style)

    init {
        createTestBoundsLayer(style)
    }

    private fun createGeoJsonSource(style: Style): GeoJsonSource =
        style.getSourceAs<GeoJsonSource>(Ids.Source)
            ?: geoJsonSource(Ids.Source).apply {
                style.addSource(this)
            }

    private fun createTestBoundsLayer(style: Style) {
        if (style.getLayer(Ids.Layer) == null) {
            val lineLayer = LineLayer(Ids.Layer, Ids.Source)
                .lineCap(LineCap.SQUARE)
                .lineJoin(LineJoin.MITER)
                .lineOpacity(1.0)
                .lineWidth(8.0)
                .lineColor("#FF5733")
            style.addLayer(lineLayer)
        }
    }

    fun drawBounds(vararg bounds: CoordinateBounds) {
        val mapboxLines = bounds.map {
            toLineStrings(it).map { line ->
                Feature.fromGeometry(line, null, UUID.randomUUID().toString())
            }
        }.flatten()
        dataSource.featureCollection(FeatureCollection.fromFeatures(mapboxLines))
    }

    private fun toLineStrings(bounds: CoordinateBounds): List<LineString> {
        val topLeft = Point.fromLngLat(bounds.west(), bounds.north())
        val bottomLeft = Point.fromLngLat(bounds.west(), bounds.south())
        val topRight = Point.fromLngLat(bounds.east(), bounds.north())
        val bottomRight = Point.fromLngLat(bounds.east(), bounds.south())
        return listOf(
            // Left vertical line.
            LineString.fromLngLats(listOf(bottomLeft, topLeft)),
            // Top horizontal line.
            LineString.fromLngLats(listOf(topLeft, topRight)),
            // Right vertical line.
            LineString.fromLngLats(listOf(topRight, bottomRight)),
            // Bottom horizontal line.
            LineString.fromLngLats(listOf(bottomRight, bottomLeft)),
        )
    }

    private object Ids {
        const val Source = "test_bounding_boxes_source"
        const val Layer = "test_bounding_boxes_layer"
    }
}