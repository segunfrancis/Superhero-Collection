package com.project.segunfrancis.superherocollection.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.segunfrancis.superherocollection.framework.MainRepositoryImpl
import com.project.segunfrancis.superherocollection.presentation.main.MainActivityViewModel
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class SuperHeroViewModelFactory @Inject constructor(private val repositoryImpl: MainRepositoryImpl) : ViewModelProvider.Factory {

    /**
     * Creates a new instance of the given `Class`.
     *
     * @param modelClass a `Class` whose instance is requested
     * @param <T>        The type parameter for the ViewModel.
     * @return a newly created ViewModel
    </T> */
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(modelClass)) {
            return MainActivityViewModel(repositoryImpl) as T
        } else {
            throw IllegalArgumentException("Unknown class name")
        }
    }
}