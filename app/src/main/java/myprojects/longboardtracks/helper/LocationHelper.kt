package myprojects.longboardtracks.helper

import android.location.Location
import android.util.Log
import org.maplibre.android.location.engine.LocationEngineCallback
import org.maplibre.android.location.engine.LocationEngineResult
import javax.inject.Inject


class LocationHelper @Inject constructor(private val toastHelper: ToastHelper) {
    fun createCallback(onLocation: (Location) -> Unit): LocationEngineCallback<LocationEngineResult> =
        object : LocationEngineCallback<LocationEngineResult> {
            override fun onSuccess(result: LocationEngineResult?) {
                result?.lastLocation?.let(onLocation)
            }

            override fun onFailure(exception: Exception) {
                Log.e("Location ex", exception.toString())
                toastHelper.showMessage("Standort nicht aktiviert")
            }
        }
}