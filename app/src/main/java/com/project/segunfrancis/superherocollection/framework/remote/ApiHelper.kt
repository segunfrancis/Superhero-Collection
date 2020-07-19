package com.project.segunfrancis.superherocollection.framework.remote

import com.project.segunfrancis.core.domain.SuperHeroEntity
import io.reactivex.Single

/**
 * Created by SegunFrancis
 */
class ApiHelper(private val serviceImpl: ApiServiceImpl) {
    fun getSuperHeroesRemote(): Single<SuperHeroEntity> {
        return serviceImpl.getSuperHeroesRemote()
    }
}