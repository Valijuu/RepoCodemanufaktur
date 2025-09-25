package myprojects.longboardtracks.pages.mapepage

import android.location.Location
import android.location.LocationListener
import android.os.Looper
import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import myprojects.longboardtracks.helper.LocationHelper
import myprojects.longboardtracks.pages.basepage.BaseViewModel
import org.maplibre.android.location.engine.LocationEngineCallback
import org.maplibre.android.location.engine.LocationEngineRequest
import org.maplibre.android.location.engine.LocationEngineResult
import org.maplibre.android.location.engine.MapLibreFusedLocationEngineImpl
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val locationHelper: LocationHelper,
    private val locationEngine: MapLibreFusedLocationEngineImpl,
    private val request: LocationEngineRequest,
) : BaseViewModel() {

    private val _location = MutableStateFlow<Location?>(null)
    private val locationCallback: LocationEngineCallback<LocationEngineResult> by lazy {
        locationHelper.createCallback { latest ->
            Log.d("LocationCallBackMapVieModel", "Received location: ${latest.longitude}, ${latest.latitude}")
            _location.update { latest }
        }
    }
    private val locationListener: LocationListener =
        locationEngine.createListener(locationCallback)

    val location = _location.asStateFlow()

    fun onPermissionResult() {
        startLocationUpdates()
    }

    private fun startLocationUpdates(){
        locationEngine.requestLocationUpdates(request, locationListener, Looper.getMainLooper())
        getLastKnownLocation()
    }

    private fun getLastKnownLocation(){
        locationEngine.getLastLocation(locationCallback)
    }

    private fun stopLocationUpdates(){
        locationEngine.removeLocationUpdates(locationListener)
    }

    override fun onCleared() {
        stopLocationUpdates()
        super.onCleared()
    }
}