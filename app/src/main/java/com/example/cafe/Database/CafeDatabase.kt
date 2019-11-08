package com.example.cafe.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(CafeModels::class), version = 1, exportSchema = false)
abstract class CafeDatabase : RoomDatabase() {

    abstract fun listCafeDAO(): CafeDataBaseDAO

    companion object {
        @Volatile
        private var INSTANCE: CafeDatabase? = null

        fun getDatabase(
            context: Context
        ): CafeDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CafeDatabase::class.java,
                    "cafe_database"
                ).allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
@Database(entities = arrayOf(SuggestModels::class), version = 1, exportSchema = false)
abstract class SuggestDatabase : RoomDatabase() {

    abstract fun listSuggestDAO(): SuggestDatabaseDAO

    companion object {
        @Volatile
        private var INSTANCE: SuggestDatabase? = null

        fun getDatabase(
            context: Context
        ): SuggestDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SuggestDatabase::class.java,
                    "suggest_database"
                ).allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}