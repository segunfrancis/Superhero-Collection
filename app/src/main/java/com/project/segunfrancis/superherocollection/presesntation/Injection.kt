package com.project.segunfrancis.superherocollection.presesntation

import androidx.lifecycle.ViewModelProvider
import com.project.segunfrancis.superherocollection.framework.remote.ApiHelper
import com.project.segunfrancis.superherocollection.framework.remote.ApiServiceImpl

/**
 * Created by SegunFrancis
 */
object Injection {
    private fun provideApiHelper(): ApiHelper {
        return ApiHelper(ApiServiceImpl())
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return SuperHeroViewModelFactory(provideApiHelper())
    }
}