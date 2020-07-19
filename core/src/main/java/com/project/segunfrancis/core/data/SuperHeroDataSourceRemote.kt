package com.project.segunfrancis.core.data

import com.project.segunfrancis.core.domain.SuperHeroEntity
import io.reactivex.Single

/**
 * Created by SegunFrancis
 */

interface SuperHeroDataSourceRemote {
    fun getSuperHeroesRemote(): Single<SuperHeroEntity>
}