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
import myprojects.longboardtracks.data.entitys.relations.RideWithTrackpoint
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

class RideDaoTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var appDatabase: AppDatabase
    private lateinit var rideDao: RideDao
    private lateinit var trackpointDao: TrackPointDao

    @Before
    fun setup() {
        hiltRule.inject() // Inject all with the annotation @Inject
        rideDao = appDatabase.rideDao()
        trackpointDao = appDatabase.trackpointDao()
    }

    @After
    fun teardown() = appDatabase.close()

    @Test
    fun insertRide_getRideById() = runTest {
        val id = insertRideAndGetId()

        val rideById = rideDao.getById(id).first()

        assertThat(rideById).isEqualTo(getSingleTestRide().copy(rideId = id))
    }

    @Test
    fun insertListRide_getAllRidesByIds() = runTest {
        val listRide = getMultipleTestRides()

        val ids = insertMultipleRidesAndGetIds()

        val ridesByIds = rideDao.getAllByIds(ids).first()

        val expectedList = listRide.mapIndexed { index, ride -> ride.copy(ids[index]) }

        assertThat(ridesByIds).containsExactlyElementsIn(expectedList)
    }

    @Test
    fun insertListRide_getAllRides() = runTest {
        val listRide = getMultipleTestRides()

        val ids = insertMultipleRidesAndGetIds()

        val allRides = rideDao.getAll().first()

        val expectedList = listRide.mapIndexed { index, ride -> ride.copy(ids[index]) }

        assertThat(allRides).containsExactlyElementsIn(expectedList)
    }

    @Test
    fun insertRideDeleteRide_getNull() = runTest {
        val ride = getSingleTestRide()

        val id = insertRideAndGetId()

        val rideAfterInsert = rideDao.getById(id).first()

        assertThat(rideAfterInsert).isEqualTo(ride.copy(id))

        rideDao.delete(ride.copy(id))

        val rideAfterDelete = rideDao.getById(id).first()

        assertThat(rideAfterDelete).isNull()
    }

    @Test
    fun insertRideDeleteRideById_getNull() = runTest {
        val ride = getSingleTestRide()

        val id = insertRideAndGetId()

        val rideAfterInsert = rideDao.getById(id).first()

        assertThat(rideAfterInsert).isEqualTo(ride.copy(id))

        rideDao.deleteById(id)

        val rideAfterDelete = rideDao.getById(id).first()

        assertThat(rideAfterDelete).isNull()
    }

    @Test
    fun insertRidesWithTrackpoints_getListRidesWithTrackpoints() = runTest {
        val rideOne = Ride(description = "I am rideOne", typ = "RelationTestOne")
        val rideTwo = Ride(description = "I am rideTwo", typ = "RelationTestTwo")

        val rideIdOne = rideDao.insert(rideOne)
        val rideIdTwo = rideDao.insert(rideTwo)

        val trackpointList = listOf(
            Trackpoint(
                rideId = rideIdOne,
                startTime = 10,
                endTime = 20,
                duration = 20,
                avgSpeed = 46.03,
                maxSpeed = 80.32,
                startPoint = 34.212,
                endPoint = 67.234
            ),
            Trackpoint(
                rideId = rideIdTwo,
                startTime = 20,
                endTime = 30,
                duration = 40,
                avgSpeed = 46.03,
                maxSpeed = 80.32,
                startPoint = 34.212,
                endPoint = 67.234
            )
        )
        val trackpointIds = trackpointDao.insertAll(trackpointList)

        val mappedExpectedList = trackpointList.mapIndexed { index, trackpoint -> trackpoint.copy(trackpointId = trackpointIds[index]) }

        val expectedList = listOf(
            RideWithTrackpoint(rideOne.copy(rideId = rideIdOne), mappedExpectedList[0]),
            RideWithTrackpoint(rideTwo.copy(rideId = rideIdTwo), mappedExpectedList[1])
        )

        val ridesWithTrackpoints = rideDao.getAllRidesWithTrackpoints().first()

        assertThat(ridesWithTrackpoints).isEqualTo(expectedList)
    }


    private fun getSingleTestRide(): Ride = Ride(description = "TestRide", typ = "Downhill")

    private fun getMultipleTestRides(): List<Ride> = mutableListOf(
        Ride(description = "Testride 1", typ = "Downhill"),
        Ride(description = "Testride 2", typ = "Cruise")
    )

    private suspend fun insertRideAndGetId(): Long =
        rideDao.insert(Ride(description = "TestRide", typ = "Downhill"))

    private suspend fun insertMultipleRidesAndGetIds(): LongArray = rideDao.insertAll(
        mutableListOf(
            Ride(description = "Testride 1", typ = "Downhill"),
            Ride(description = "Testride 2", typ = "Cruise")
        )
    )
}