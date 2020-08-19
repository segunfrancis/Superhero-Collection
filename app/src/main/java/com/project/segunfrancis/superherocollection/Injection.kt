package com.project.segunfrancis.superherocollection

import androidx.lifecycle.ViewModelProvider
import com.project.segunfrancis.superherocollection.framework.MainRepositoryImpl
import com.project.segunfrancis.superherocollection.framework.remote.SuperHeroService
import com.project.segunfrancis.superherocollection.presentation.SuperHeroViewModelFactory

/**
 * Created by SegunFrancis
 */
object Injection {
    private fun provideRepository(): MainRepositoryImpl {
        return MainRepositoryImpl(SuperHeroService.create())
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return SuperHeroViewModelFactory(
            provideRepository()
        )
    }
}