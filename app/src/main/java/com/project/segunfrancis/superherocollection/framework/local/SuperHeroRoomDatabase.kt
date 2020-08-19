package com.project.segunfrancis.superherocollection.framework.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {
        private var superHeroRoomInstance: SuperHeroRoomDatabase? = null

        fun getInstance(context: Context): SuperHeroRoomDatabase? {
            if (superHeroRoomInstance == null) {
                synchronized(SuperHeroRoomDatabase::class.java) {
                    if (superHeroRoomInstance == null) {
                        superHeroRoomInstance = Room.databaseBuilder<SuperHeroRoomDatabase>(
                            context.applicationContext,
                            SuperHeroRoomDatabase::class.java,
                            "super_hero_database"
                        ).build()
                    }
                }
            }
            return superHeroRoomInstance
        }
    }
}