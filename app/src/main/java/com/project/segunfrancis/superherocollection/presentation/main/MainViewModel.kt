package com.project.segunfrancis.superherocollection.presentation.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.project.segunfrancis.superherocollection.framework.MainRepository
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

/**
 * Created by SegunFrancis
 */

class MainViewModel @ViewModelInject constructor(private val repository: MainRepository) : ViewModel() {

    var superHeroesData: Flow<PagingData<CharacterEntity>>

    init {
        superHeroesData = fetchSuperHeroes()
    }

    private fun fetchSuperHeroes(): Flow<PagingData<CharacterEntity>> {
        return repository.getSuperHeroesRemote()
            .cachedIn(viewModelScope)
    }

    fun setFavorite(character: CharacterEntity) {
        repository.setFavorite(character)
    }

    fun removeFavorite(character: CharacterEntity) {
        repository.removeFavorite(character)
    }

    val allFavorites: LiveData<List<CharacterEntity>> = liveData {
        repository.getAllFavorites().collect {
            emit(it)
        }
    }
}