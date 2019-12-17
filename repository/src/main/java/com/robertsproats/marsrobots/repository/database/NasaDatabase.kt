package com.robertsproats.marsrobots.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.robertsproats.marsrobots.repository.database.converters.ListConverters

@Database(entities = [NasaEntity::class], version = 1)
@TypeConverters(ListConverters::class)
abstract class NasaDatabase : RoomDatabase() {
    abstract fun nasaDao(): NasaDao

    companion object {
        private var INSTANCE: NasaDatabase? = null

        fun getNasaDataBase(context: Context): NasaDatabase? {
            if (INSTANCE == null) {
                synchronized(NasaDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, NasaDatabase::class.java, "nasaDB").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}