package com.project.segunfrancis.superherocollection.framework.remote

import com.project.segunfrancis.core.data.SuperHeroDataSourceRemote
import com.project.segunfrancis.core.domain.SuperHeroEntity
import com.project.segunfrancis.superherocollection.presesntation.utils.AppConstants.BASE_URL
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single

/**
 * Created by SegunFrancis
 */
class ApiServiceImpl : SuperHeroDataSourceRemote {
    override fun getSuperHeroesRemote(): Single<SuperHeroEntity> {
        return Rx2AndroidNetworking.get(BASE_URL).build()
            .getObjectSingle(SuperHeroEntity::class.java)
    }
}