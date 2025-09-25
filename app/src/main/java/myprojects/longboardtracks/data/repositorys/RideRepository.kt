package myprojects.longboardtracks.data.repositorys

import kotlinx.coroutines.flow.Flow
import myprojects.longboardtracks.data.daos.RideDao
import myprojects.longboardtracks.data.daos.TrackPointDao
import myprojects.longboardtracks.data.entitys.Ride
import myprojects.longboardtracks.data.entitys.relations.RideWithTrackpoint
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RideRepository @Inject constructor(
    private val rideDao: RideDao,
    private val trackPointDao: TrackPointDao
) {
    //Get
    fun getAllRides(): Flow<List<Ride>> = rideDao.getAll()

    fun getAllByIds(rideIds: LongArray): Flow<List<Ride>> = rideDao.getAllByIds(rideIds)

    fun getById(rideId: Long): Flow<Ride?> = rideDao.getById(rideId)

    fun getAllRidesWithTrackpoints(): Flow<List<RideWithTrackpoint>> = rideDao.getAllRidesWithTrackpoints()

    //Insert
    suspend fun insert(ride: Ride): Long = rideDao.insert(ride)

    suspend fun insertAll(rides: List<Ride>): LongArray = rideDao.insertAll(rides)

    //Delete
    suspend fun delete(ride: Ride) = rideDao.delete(ride)

    suspend fun deleteById(rideId: Long) = rideDao.deleteById(rideId)
}