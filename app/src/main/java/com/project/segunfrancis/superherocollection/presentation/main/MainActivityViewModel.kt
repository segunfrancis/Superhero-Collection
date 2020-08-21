package com.project.segunfrancis.superherocollection.presentation.main

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.project.segunfrancis.superherocollection.framework.MainRepositoryImpl
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

/**
 * Created by SegunFrancis
 */

class MainActivityViewModel(private val repositoryImpl: MainRepositoryImpl) : ViewModel() {

    private val _scrollYPosition = MutableLiveData<Int>()
    val scrollYPosition: LiveData<Int>
        get() = _scrollYPosition

    private val _showBadge = MutableLiveData<Boolean>(false)
    val showBadge: LiveData<Boolean>
        get() = _showBadge

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

    fun setFavorite(character: CharacterEntity) {
         repositoryImpl.setFavorite(character)
    }

    fun removeFavorite(character: CharacterEntity) {
        repositoryImpl.removeFavorite(character)
    }

    val allFavorites: LiveData<List<CharacterEntity>> = liveData {
        repositoryImpl.getAllFavorites().collect {
            emit(it)
        }
    }

    fun setShowBadge(value: Boolean) {
        _showBadge.value = value
    }
}