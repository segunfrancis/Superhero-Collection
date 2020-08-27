package com.project.segunfrancis.superherocollection.di

import android.content.Context
import androidx.room.Room
import com.project.segunfrancis.superherocollection.framework.local.SuperHeroDao
import com.project.segunfrancis.superherocollection.framework.local.SuperHeroRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Created by SegunFrancis
 */

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SuperHeroRoomDatabase {
        var superHeroRoomInstance: SuperHeroRoomDatabase
        synchronized(SuperHeroRoomDatabase::class.java) {
            superHeroRoomInstance = Room.databaseBuilder<SuperHeroRoomDatabase>(
                context.applicationContext,
                SuperHeroRoomDatabase::class.java,
                "super_hero_database"
            ).build()
        }
        return superHeroRoomInstance
    }

    @Provides
    fun provideSuperHeroDao(database: SuperHeroRoomDatabase): SuperHeroDao {
        return database.dao()
    }
}