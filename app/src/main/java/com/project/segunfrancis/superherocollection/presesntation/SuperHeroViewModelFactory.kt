package com.project.segunfrancis.superherocollection.presesntation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.segunfrancis.superherocollection.framework.MainRepository
import com.project.segunfrancis.superherocollection.framework.remote.ApiHelper
import com.project.segunfrancis.superherocollection.presesntation.main.MainActivityViewModel

/**
 * Created by SegunFrancis
 */

class SuperHeroViewModelFactory(private val helper: ApiHelper) : ViewModelProvider.Factory {

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
            return MainActivityViewModel(MainRepository(helper)) as T
        } else {
            throw IllegalArgumentException("Unknown class name")
        }
    }
}