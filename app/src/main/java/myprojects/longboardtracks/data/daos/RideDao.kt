package myprojects.longboardtracks.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import myprojects.longboardtracks.data.entitys.Ride
import myprojects.longboardtracks.data.entitys.relations.RideWithTrackpoint

@Dao
interface RideDao {

    //Get Operations
    @Query("SELECT * FROM rides")
    fun getAll(): Flow<List<Ride>>

    @Query("SELECT * FROM rides WHERE rideId IN (:rideIds)")
    fun getAllByIds(rideIds: LongArray): Flow<List<Ride>>

    @Query("SELECT * FROM rides WHERE rideId = :rideId")
    fun getById(rideId: Long): Flow<Ride?>

    @Transaction
    @Query("SELECT * FROM rides")
    fun getAllRidesWithTrackpoints(): Flow<List<RideWithTrackpoint>>

    //Insert + Update Operation
    @Upsert
    suspend fun insert(ride: Ride): Long

    @Upsert
    suspend fun insertAll(rides: List<Ride>): LongArray

    //Delete Operations
    @Delete
    suspend fun delete(ride: Ride)

    @Query("DELETE FROM rides WHERE rideId = :rideId")
    suspend fun deleteById(rideId: Long)
}