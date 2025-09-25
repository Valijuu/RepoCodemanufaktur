package myprojects.longboardtracks.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import myprojects.longboardtracks.data.daos.RideDao
import myprojects.longboardtracks.data.daos.TrackPointDao
import myprojects.longboardtracks.data.entitys.Ride
import myprojects.longboardtracks.data.entitys.Trackpoint

@Database(entities = [Ride::class, Trackpoint::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun rideDao(): RideDao
    abstract fun trackpointDao(): TrackPointDao
}