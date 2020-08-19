package com.project.segunfrancis.superherocollection.framework

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity
import com.project.segunfrancis.superherocollection.framework.remote.SuperHeroService
import com.project.segunfrancis.superherocollection.presentation.SuperHeroPagingSource
import kotlinx.coroutines.flow.Flow

/**
 * Created by SegunFrancis
 */
class MainRepositoryImpl(private val service: SuperHeroService): MainRepository {
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
        TODO("Not yet implemented")
    }

    override fun getFavorites(): List<CharacterEntity> {
        TODO("Not yet implemented")
    }
}