package myprojects.longboardtracks.data.repositorys

import kotlinx.coroutines.flow.Flow
import myprojects.longboardtracks.data.daos.TrackPointDao
import myprojects.longboardtracks.data.entitys.Trackpoint
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrackpointRepository @Inject constructor(
    private val trackPointDao: TrackPointDao
) {
    //Get
    fun getAll(): Flow<List<Trackpoint>> = trackPointDao.getAll()

    fun getAllByIds(trackpointIds: LongArray): Flow<List<Trackpoint>> =
        trackPointDao.getAllByIds(trackpointIds)

    fun getById(trackpointId: Long): Flow<Trackpoint?> = trackPointDao.getById(trackpointId)

    //Insert
    suspend fun insert(trackpoint: Trackpoint): Long = trackPointDao.insert(trackpoint)

    suspend fun insertAll(trackpointList: List<Trackpoint>): LongArray =
        trackPointDao.insertAll(trackpointList)

    //Delete
    suspend fun delete(trackpoint: Trackpoint) = trackPointDao.delete(trackpoint)

    suspend fun deleteById(trackpointId: Long) = trackPointDao.deleteById(trackpointId)
}