package com.project.segunfrancis.core.data

import com.project.segunfrancis.core.domain.SuperHeroEntity
import io.reactivex.Single

/**
 * Created by SegunFrancis
 */

class SuperHeroRepository(
    private val dataSourceRemote: SuperHeroDataSourceRemote,
    private val dataSourceLocal: SuperHeroDataSourceLocal
) {
    fun getSuperHeroesRemote(): Single<SuperHeroEntity> {
        return dataSourceRemote.getSuperHeroesRemote()
    }

    suspend fun setSuperHeroes(superHero: SuperHeroEntity) {
        dataSourceLocal.setSuperHeroes(superHero)
    }

    suspend fun getSuperHeroesLocal(): SuperHeroEntity {
        return dataSourceLocal.getSuperHeroesLocal()
    }
}