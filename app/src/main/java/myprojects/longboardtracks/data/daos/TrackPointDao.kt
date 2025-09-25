package myprojects.longboardtracks.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import myprojects.longboardtracks.data.entitys.Ride
import myprojects.longboardtracks.data.entitys.Trackpoint

@Dao
interface TrackPointDao {
    //Get Operations
    @Query("SELECT * FROM trackpoints")
    fun getAll(): Flow<List<Trackpoint>>

    @Query("SELECT * FROM trackpoints WHERE trackpointId IN (:trackpointIds)")
    fun getAllByIds(trackpointIds: LongArray): Flow<List<Trackpoint>>

    @Query("SELECT * FROM trackpoints WHERE trackpointId = :trackpointId")
    fun getById(trackpointId: Long): Flow<Trackpoint?>

    //Update + Insert Operations
    @Upsert
    suspend fun insert(trackpoint: Trackpoint): Long

    @Upsert
    suspend fun insertAll(trackpointList: List<Trackpoint>): LongArray

    //Delete Operations
    @Delete
    suspend fun delete(trackpoint: Trackpoint)

    @Query("DELETE FROM trackpoints WHERE trackpointId = :trackpointId")
    suspend fun deleteById(trackpointId: Long)
}