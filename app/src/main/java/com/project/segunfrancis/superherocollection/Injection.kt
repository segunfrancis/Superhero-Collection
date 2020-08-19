package com.project.segunfrancis.superherocollection

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import com.project.segunfrancis.superherocollection.framework.MainRepositoryImpl
import com.project.segunfrancis.superherocollection.framework.remote.SuperHeroService
import com.project.segunfrancis.superherocollection.presentation.SuperHeroViewModelFactory
import com.project.segunfrancis.superherocollection.presentation.utils.AppConstants.SHARED_PREF_KEY

/**
 * Created by SegunFrancis
 */

class Injection : Application() {
    private fun provideRepository(): MainRepositoryImpl {
        return MainRepositoryImpl(SuperHeroService.create())
    }

    val viewModelFactory: ViewModelProvider.Factory
        get() = SuperHeroViewModelFactory(provideRepository())

    val sharedPreferences: SharedPreferences
        get() = getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE)

}