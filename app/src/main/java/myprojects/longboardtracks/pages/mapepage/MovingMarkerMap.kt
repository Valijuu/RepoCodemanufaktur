package myprojects.longboardtracks.pages.mapepage

import android.graphics.BitmapFactory
import android.location.Location
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import dev.sargunv.maplibrecompose.compose.MaplibreMap
import dev.sargunv.maplibrecompose.compose.layer.SymbolLayer
import dev.sargunv.maplibrecompose.compose.rememberCameraState
import dev.sargunv.maplibrecompose.compose.source.rememberGeoJsonSource
import dev.sargunv.maplibrecompose.core.CameraPosition
import dev.sargunv.maplibrecompose.core.source.GeoJsonData
import dev.sargunv.maplibrecompose.expressions.dsl.const
import dev.sargunv.maplibrecompose.expressions.dsl.image
import io.github.dellisd.spatialk.geojson.Position
import myprojects.longboardtracks.BuildConfig.MAPTILER_KEY
import myprojects.longboardtracks.R
import org.maplibre.geojson.Feature
import org.maplibre.geojson.FeatureCollection
import org.maplibre.geojson.Point

@Composable
fun MovingMarkerMap(
    location: Location?,
    sourceId: String = "moving-marker-source"
) {
    val mapTilerKey = MAPTILER_KEY
    val context = LocalContext.current
    val cameraState = rememberCameraState()
    val mapZoom = if (location == null) 0.0 else 15.0

    val pinImage = remember {
        BitmapFactory.decodeResource(context.resources, R.drawable.longboard_small).asImageBitmap()
    }

    //Animate to new Position
    LaunchedEffect(location) {
        cameraState.animateTo(
            CameraPosition(
                target = Position(location?.longitude ?: 0.0, location?.latitude ?: 0.0),
                zoom = mapZoom
            )
        )
    }

    MaplibreMap(
        modifier = Modifier.fillMaxSize(),
        cameraState = cameraState,
        styleUri = "https://api.maptiler.com/maps/streets-v2/style.json?key=$mapTilerKey"
    ) {
        val geoJsonSource = rememberGeoJsonSource(
            id = sourceId,
            data = GeoJsonData.JsonString(FeatureCollection.fromFeatures(emptyList()).toJson())
        )
        val point = remember(location) {
            location?.let { Point.fromLngLat(it.longitude, it.latitude) }
        }

        //Overwrite Data when new Point received
        LaunchedEffect(location) {
            val fc = FeatureCollection.fromFeatures(listOf(Feature.fromGeometry(point)))
            geoJsonSource.setData(GeoJsonData.JsonString(fc.toJson()))
        }

        SymbolLayer(
            id = "icon-marker-layer",
            source = geoJsonSource,
            iconImage = image(pinImage),
            iconSize = const(0.05f),
            iconAllowOverlap = const(true),
            iconIgnorePlacement = const(true)
        )
    }
}