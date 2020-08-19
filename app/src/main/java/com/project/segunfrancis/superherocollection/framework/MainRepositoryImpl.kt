package com.project.segunfrancis.superherocollection.framework

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity
import com.project.segunfrancis.superherocollection.framework.local.SuperHeroDao
import com.project.segunfrancis.superherocollection.framework.remote.SuperHeroService
import com.project.segunfrancis.superherocollection.presentation.SuperHeroPagingSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * Created by SegunFrancis
 */
class MainRepositoryImpl(private val service: SuperHeroService, private val dao: SuperHeroDao): MainRepository {
    override fun getSuperHeroesRemote(): Flow<PagingData<CharacterEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SuperHeroPagingSource(service) }
        ).flow
    }

    override fun setFavorite(character: CharacterEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.setFavorite(character)
        }
    }

    override fun getAllFavorites(): Flow<List<CharacterEntity>> {
        return dao.getAllFavorites()
    }

    override fun removeFavorite(character: CharacterEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.removeFavorite(character)
        }
    }
}