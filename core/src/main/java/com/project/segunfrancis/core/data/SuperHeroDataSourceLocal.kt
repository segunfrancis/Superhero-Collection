package com.project.segunfrancis.core.data

import com.project.segunfrancis.core.domain.SuperHeroEntity

/**
 * Created by SegunFrancis
 */

interface SuperHeroDataSourceLocal {
    suspend fun setSuperHeroes(superHero: SuperHeroEntity)

    suspend fun getSuperHeroesLocal(): SuperHeroEntity
}