package myprojects.longboardtracks.data.entitys

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "rides")
data class Ride(
    @PrimaryKey(autoGenerate = true) val rideId: Long = 0,
    val description: String?,
    val typ: String?
)
