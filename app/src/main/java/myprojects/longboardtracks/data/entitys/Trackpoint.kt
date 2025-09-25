package myprojects.longboardtracks.data.entitys

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "trackpoints",
    foreignKeys = [
        ForeignKey(
            entity = Ride::class, // Parent
            parentColumns = ["rideId"], // Parent primary key
            childColumns = ["rideId"], // Parent Id in Child
            onDelete = ForeignKey.CASCADE, // Delete children if parent deletes
            onUpdate = ForeignKey.NO_ACTION
        )
    ],
    indices = [Index(value = ["rideId"], unique = true)]
)
data class Trackpoint(
    @PrimaryKey(autoGenerate = true) val trackpointId: Long = 0,
    val rideId: Long,
    val startTime: Long?,
    val endTime: Long?,
    val duration: Long?,
    val avgSpeed: Double?,
    val maxSpeed: Double?,
    val startPoint: Double?,
    val endPoint: Double?
)
