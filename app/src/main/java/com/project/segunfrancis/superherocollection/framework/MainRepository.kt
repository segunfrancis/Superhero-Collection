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
class MainRepository(private val service: SuperHeroService) {
    fun getSuperHeroesRemote(): Flow<PagingData<CharacterEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SuperHeroPagingSource(service) }
        ).flow
    }
}