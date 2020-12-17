package com.project.segunfrancis.superherocollection.framework.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.project.segunfrancis.superherocollection.framework.domain.CharacterEntity

/**
 * Created by SegunFrancis
 */

@Database(entities = [CharacterEntity::class], exportSchema = false, version = 1)
@TypeConverters(Converters::class)
abstract class SuperHeroRoomDatabase : RoomDatabase() {
    abstract fun dao(): SuperHeroDao
}