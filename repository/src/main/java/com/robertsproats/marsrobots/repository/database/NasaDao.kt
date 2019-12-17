package com.robertsproats.marsrobots.repository.database

import androidx.room.*

@Dao
interface NasaDao {

    @Query("SELECT * FROM nasa_entity_list")
    fun getNasaList(): List<NasaEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Transaction
    suspend fun insertNasaList(nasaList: List<NasaEntity>)

}