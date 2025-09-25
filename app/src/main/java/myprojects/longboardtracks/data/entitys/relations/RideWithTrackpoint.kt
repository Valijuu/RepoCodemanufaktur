package myprojects.longboardtracks.data.entitys.relations

import androidx.room.Embedded
import androidx.room.Relation
import myprojects.longboardtracks.data.entitys.Ride
import myprojects.longboardtracks.data.entitys.Trackpoint

//only a POJO
data class RideWithTrackpoint(
    @Embedded val ride: Ride,
    @Relation(
        parentColumn = "rideId",
        entityColumn = "rideId"
    )
    val trackpoint: Trackpoint
)
