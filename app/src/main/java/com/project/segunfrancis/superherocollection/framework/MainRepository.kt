package com.project.segunfrancis.superherocollection.framework

import com.project.segunfrancis.core.domain.SuperHeroEntity
import com.project.segunfrancis.superherocollection.framework.remote.ApiHelper
import io.reactivex.Single

/**
 * Created by SegunFrancis
 */
class MainRepository(private val helper: ApiHelper) {
    fun getSuperHeroesRemote(): Single<SuperHeroEntity> {
        return helper.getSuperHeroesRemote()
    }
}