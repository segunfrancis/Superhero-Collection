package com.project.segunfrancis.superherocollection.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.project.segunfrancis.superherocollection.framework.MainRepositoryImpl
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by SegunFrancis
 */

class MainActivityViewModel(private val repositoryImpl: MainRepositoryImpl) : ViewModel() {

    private val _scrollYPosition = MutableLiveData<Int>()
    val scrollYPosition: LiveData<Int>
        get() = _scrollYPosition

    var superHeroesData: Flow<PagingData<CharacterEntity>>

    init {
        superHeroesData = fetchSuperHeroes()
    }

    private fun fetchSuperHeroes(): Flow<PagingData<CharacterEntity>> {
        return repositoryImpl.getSuperHeroesRemote()
            .cachedIn(viewModelScope)
    }

    fun setScrollYPosition(position: Int) {
        _scrollYPosition.value = position
    }
}