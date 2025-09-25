package myprojects.longboardtracks.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import myprojects.longboardtracks.data.database.AppDatabase
import myprojects.longboardtracks.data.daos.RideDao
import myprojects.longboardtracks.data.daos.TrackPointDao
import javax.inject.Singleton

//Configuration for Hilts dependency graph
@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
     Room.databaseBuilder(context, AppDatabase::class.java, "longboardTracks-database").build()

    @Provides
    fun provideRideDao(database: AppDatabase): RideDao = database.rideDao()

    @Provides
    fun provideTrackPointDao(database: AppDatabase): TrackPointDao = database.trackpointDao()
}