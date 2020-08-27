package com.project.segunfrancis.superherocollection.di

import android.content.Context
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
        return SuperHeroRoomDatabase.getInstance(context.applicationContext)!!
    }

    @Provides
    fun provideSuperHeroDao(database: SuperHeroRoomDatabase): SuperHeroDao {
        return database.dao()
    }
}