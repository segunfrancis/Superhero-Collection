package com.project.segunfrancis.superherocollection.framework.remote

import com.project.segunfrancis.superherocollection.framework.domain.SuperHeroEntity
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by SegunFrancis
 */
interface SuperHeroService {

    @GET("cursor/{cursor}")
    suspend fun getSuperHeroesRemote(@Path("cursor") cursor: Any?): SuperHeroEntity
}