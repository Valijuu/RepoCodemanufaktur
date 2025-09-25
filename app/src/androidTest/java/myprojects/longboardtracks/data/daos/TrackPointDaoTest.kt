package myprojects.longboardtracks.data.daos

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import myprojects.longboardtracks.data.database.AppDatabase
import myprojects.longboardtracks.data.entitys.Ride
import myprojects.longboardtracks.data.entitys.Trackpoint
import myprojects.longboardtracks.di.DataBaseModule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(DataBaseModule::class)
@RunWith(AndroidJUnit4::class)
class TrackPointDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var appDatabase: AppDatabase
    private lateinit var trackPointDao: TrackPointDao
    private lateinit var rideDao: RideDao

    @Before
    fun setup() {
        hiltRule.inject() // Inject all with the annotation @Inject
        trackPointDao = appDatabase.trackpointDao()
        rideDao = appDatabase.rideDao()
    }

    @After
    fun teardown() = appDatabase.close()

    @Test
    fun insertRideAndTrackpoint_getTrackpoint() = runTest {
        val trackpoint = Trackpoint(
            rideId = givenRideId(),
            startTime = 10L, endTime = 15L, duration = 45L,
            avgSpeed = 12.0, maxSpeed = 20.0,
            startPoint = 12.123,
            endPoint = 15.123
        )

        val trackpointId = trackPointDao.insert(trackpoint)

        val trackpointFromDB = trackPointDao.getById(trackpointId).first()

        assertThat(trackpointFromDB).isEqualTo(trackpoint.copy(trackpointId = trackpointId))
    }

    private suspend fun givenRideId(
    ): Long = rideDao.insert(Ride(description = "TestRide", typ = "Cruise"))
}