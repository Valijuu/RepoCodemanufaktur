package myprojects.longboardtracks.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.maplibre.android.location.engine.LocationEngineRequest
import org.maplibre.android.location.engine.MapLibreFusedLocationEngineImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {
    @Provides
    @Singleton
    fun provideLocationRequest(): LocationEngineRequest =
        LocationEngineRequest.Builder(1000L)
            .setFastestInterval(500L)
            .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
            .build()

    @Provides
    @Singleton
    fun provideLocationEngine(
        @ApplicationContext context: Context
    ): MapLibreFusedLocationEngineImpl =
        MapLibreFusedLocationEngineImpl(context)
}