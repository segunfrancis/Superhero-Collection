package com.project.segunfrancis.superherocollection

import androidx.lifecycle.ViewModelProvider
import com.project.segunfrancis.superherocollection.framework.MainRepository
import com.project.segunfrancis.superherocollection.framework.remote.SuperHeroService
import com.project.segunfrancis.superherocollection.presesntation.SuperHeroViewModelFactory

/**
 * Created by SegunFrancis
 */
object Injection {
    private fun provideRepository(): MainRepository {
        return MainRepository(SuperHeroService.create())
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return SuperHeroViewModelFactory(
            provideRepository()
        )
    }
}