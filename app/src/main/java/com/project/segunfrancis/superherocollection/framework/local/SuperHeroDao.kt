package com.project.segunfrancis.superherocollection.framework.local

import androidx.room.*
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity

/**
 * Created by SegunFrancis
 */

@Dao
interface SuperHeroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setFavorite(character: CharacterEntity)

    @Query("SELECT * FROM character_table")
    fun getAllFavorites(): List<CharacterEntity>

    @Delete
    fun removeFavorite(character: CharacterEntity)
}