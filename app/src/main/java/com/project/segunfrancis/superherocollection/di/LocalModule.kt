package com.project.segunfrancis.superherocollection.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.project.segunfrancis.superherocollection.framework.local.SuperHeroDao
import com.project.segunfrancis.superherocollection.framework.local.SuperHeroRoomDatabase
import com.project.segunfrancis.superherocollection.presentation.utils.AppConstants
import com.project.segunfrancis.superherocollection.presentation.utils.AppConstants.DATABASE_NAME
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
object LocalModule {

    @Provides
    @Singleton
    fun provideSuperHeroDao(@ApplicationContext context: Context): SuperHeroDao {
        val database = Room.databaseBuilder(
            context.applicationContext,
            SuperHeroRoomDatabase::class.java,
            DATABASE_NAME
        ).build()
        return database.dao()
    }

    @Provides
    @LikePreference
    fun sharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(AppConstants.SHARED_PREF_KEY, Context.MODE_PRIVATE)
    }

    @Provides
    @SettingsPreference
    fun settingsPreference(@ApplicationContext context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context.applicationContext)
    }
}