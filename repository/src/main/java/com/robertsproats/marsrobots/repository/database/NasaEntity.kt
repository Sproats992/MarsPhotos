package com.robertsproats.marsrobots.repository.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.robertsproats.marsrobots.repository.database.converters.ListConverters
import com.robertsproats.marsrobots.repository.model.NasaItem

@Entity(tableName = "nasa_entity_list")
@TypeConverters(ListConverters::class)
data class NasaEntity(
        @PrimaryKey(autoGenerate = true)
        val primaryKey: Int,
        val data: List<NasaItem.NasaDataItem>?,
        val links: List<NasaItem.NasaLinksItem>?
)

