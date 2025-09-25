package myprojects.longboardtracks.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import myprojects.longboardtracks.data.daos.RideDao
import myprojects.longboardtracks.data.daos.TrackPointDao
import myprojects.longboardtracks.data.database.AppDatabase
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataBaseModule::class]
) // Only Allow to use this in Tests
object TestDataBaseModule {
    @Provides
    @Singleton
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

    @Provides
    fun provideRideDao(
        db: AppDatabase
    ): RideDao = db.rideDao()


    @Provides
    fun provideTrackPointDao(
        db: AppDatabase
    ): TrackPointDao = db.trackpointDao()
}