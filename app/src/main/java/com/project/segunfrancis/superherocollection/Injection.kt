package com.project.segunfrancis.superherocollection

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.project.segunfrancis.superherocollection.framework.MainRepositoryImpl
import com.project.segunfrancis.superherocollection.framework.local.SuperHeroRoomDatabase
import com.project.segunfrancis.superherocollection.framework.remote.SuperHeroService
import com.project.segunfrancis.superherocollection.presentation.SuperHeroViewModelFactory
import com.project.segunfrancis.superherocollection.presentation.utils.AppConstants.SHARED_PREF_KEY
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by SegunFrancis
 */

@HiltAndroidApp
class Injection : Application() {
    /*private fun provideRepository(): MainRepositoryImpl {
        return MainRepositoryImpl(
            SuperHeroService.create(),
            SuperHeroRoomDatabase.getInstance(this.applicationContext)!!.dao()
        )
    }

    val viewModelFactory: ViewModelProvider.Factory
        get() = SuperHeroViewModelFactory(provideRepository())*/

    val sharedPreferences: SharedPreferences
        get() = getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)

    val settingsPreferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(this.applicationContext)
}