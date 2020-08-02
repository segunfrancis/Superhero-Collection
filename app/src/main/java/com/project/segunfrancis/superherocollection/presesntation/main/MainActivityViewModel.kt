package com.project.segunfrancis.superherocollection.presesntation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.project.segunfrancis.superherocollection.framework.MainRepository
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * Created by SegunFrancis
 */

class MainActivityViewModel(private val repository: MainRepository) : ViewModel() {

    private val _progress = MutableLiveData<Float>()
    val progress: LiveData<Float>
        get() = _progress
    private var startProgress = 0F
    private val endProgress = 65F

    init {
        viewModelScope.launch {
            getProgress()
        }
    }

    fun fetchSuperHeroes(): Flow<PagingData<CharacterEntity>> {
        return repository.getSuperHeroesRemote()
            .cachedIn(viewModelScope)
    }

    private suspend fun getProgress() {
        while (startProgress < endProgress) {
            _progress.value = startProgress
            startProgress++
            delay(15)
        }
    }
}