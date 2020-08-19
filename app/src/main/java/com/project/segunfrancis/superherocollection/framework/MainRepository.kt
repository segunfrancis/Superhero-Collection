package com.project.segunfrancis.superherocollection.framework

import androidx.paging.PagingData
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by SegunFrancis
 */

interface MainRepository {
    fun getSuperHeroesRemote(): Flow<PagingData<CharacterEntity>>
    fun setFavorite(character: CharacterEntity)
    fun getAllFavorites(): Flow<List<CharacterEntity>>
    fun removeFavorite(character: CharacterEntity)
}