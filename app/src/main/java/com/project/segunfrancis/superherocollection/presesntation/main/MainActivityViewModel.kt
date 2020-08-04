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

    private val _scrollYPosition = MutableLiveData<Int>()
    val scrollYPosition: LiveData<Int>
        get() = _scrollYPosition

    var superHeroesData: Flow<PagingData<CharacterEntity>>

    init {
        superHeroesData = fetchSuperHeroes()
    }

    private fun fetchSuperHeroes(): Flow<PagingData<CharacterEntity>> {
        return repository.getSuperHeroesRemote()
            .cachedIn(viewModelScope)
    }

    fun setScrollYPosition(position: Int) {
        _scrollYPosition.value = position
    }
}