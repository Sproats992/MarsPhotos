package com.robertsproats.marsrobots.repository.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.robertsproats.marsrobots.repository.model.NasaItem.NasaDataItem
import com.robertsproats.marsrobots.repository.model.NasaItem.NasaLinksItem

class ListConverters {

    @TypeConverter
    fun fromDataItemString(value: String): List<NasaDataItem> {
        val listType = object : TypeToken<List<NasaDataItem>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromDataItemList(list: MutableList<NasaDataItem>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromLinksItemString(value: String): List<NasaLinksItem> {
        val listType = object : TypeToken<List<NasaLinksItem>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromLinksItemList(list: MutableList<NasaLinksItem>): String {
        return Gson().toJson(list)
    }

}