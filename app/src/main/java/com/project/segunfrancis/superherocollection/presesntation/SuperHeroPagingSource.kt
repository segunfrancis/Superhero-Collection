package com.project.segunfrancis.superherocollection.presesntation

import android.util.Log
import androidx.paging.PagingSource
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity
import com.project.segunfrancis.superherocollection.framework.domain.SuperHeroEntity
import com.project.segunfrancis.superherocollection.framework.remote.SuperHeroService
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by SegunFrancis
 */

class SuperHeroPagingSource(private val service: SuperHeroService) :
    PagingSource<Int, CharacterEntity>() {
    /**
     * Loading API for [PagingSource].
     *
     * Implement this method to trigger your async load (e.g. from database or network).
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterEntity> {
        var position: Int? = params.key
        val response: SuperHeroEntity
        val heroes: List<CharacterEntity>
        return try {
            if (position == null) {
                response = service.getSuperHeroesRemote("")
                heroes = response.characters
                position = 0
                position += response.nextCursor
                LoadResult.Page(
                    data = heroes,
                    prevKey = response.previousCursor,
                    nextKey = response.nextCursor
                )
            } else {
                response = service.getSuperHeroesRemote(position)
                heroes = response.characters
                Log.d("SuperHeroPagingSource", "Size: ${heroes.size}")
                LoadResult.Page(
                    data = heroes,
                    prevKey = response.previousCursor,
                    nextKey = response.nextCursor
                )
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}