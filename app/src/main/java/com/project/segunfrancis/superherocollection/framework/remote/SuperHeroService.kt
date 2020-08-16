package com.project.segunfrancis.superherocollection.framework.remote

import com.project.segunfrancis.superherocollection.framework.domain.SuperHeroEntity
import com.project.segunfrancis.superherocollection.presentation.utils.AppConstants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by SegunFrancis
 */
interface SuperHeroService {

    @GET("cursor/{cursor}")
    suspend fun getSuperHeroesRemote(@Path("cursor") cursor: Any?): SuperHeroEntity

    companion object {
        fun create(): SuperHeroService {
            val logger = HttpLoggingInterceptor()
            logger.level = Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(SuperHeroService::class.java)
        }
    }
}