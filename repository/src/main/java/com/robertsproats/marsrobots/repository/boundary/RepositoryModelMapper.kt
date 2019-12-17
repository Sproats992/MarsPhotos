package com.robertsproats.marsrobots.repository.boundary

import com.robertsproats.marsrobots.domain.model.DomainSimpleMarsRobotsModel
import com.robertsproats.marsrobots.repository.database.NasaEntity
import com.robertsproats.marsrobots.repository.model.NasaItem
import com.robertsproats.marsrobots.repository.model.NasaItem.NasaDataItem
import com.robertsproats.marsrobots.repository.model.NasaItem.NasaLinksItem
import com.robertsproats.marsrobots.repository.model.SimpleMarsRobotsModel
import com.robertsproats.repository.services.response.NasaMarsResponse
import javax.inject.Inject

class RepositoryModelMapper @Inject constructor() {

    fun map(itemList: List<NasaItem>): List<NasaEntity> {

        return List(size = itemList.size) {
            NasaEntity(
                    primaryKey = 0,
                    data = itemList[it].data,
                    links = itemList[it].links
            )
        }
    }

    fun mapEntityListToNasaList(entityList: List<NasaEntity>): List<NasaItem> {
        return List(size = entityList.size) {
            NasaItem(
                    data = entityList[it].data,
                    links = entityList[it].links
            )
        }
    }

    fun mapToDomainSimpleModel(model: SimpleMarsRobotsModel): DomainSimpleMarsRobotsModel {
        return DomainSimpleMarsRobotsModel(
                itemList = List(size = model.itemList.size) {
                    DomainSimpleMarsRobotsModel.DomainSimpleMarsRobotsItem(
                            imageAddress = model.itemList[it].imageAddress,
                            date = model.itemList[it].date,
                            title = model.itemList[it].title
                    )
                }
        )
    }

    fun mapToSimpleModel(itemList: List<NasaItem>): SimpleMarsRobotsModel {
        return SimpleMarsRobotsModel(
                itemList = List(size = itemList.size) {
                    SimpleMarsRobotsModel.SimpleMarsRobotsItem(
                            imageAddress = itemList[it].links?.get(0)?.href,
                            date = trivialDateFormat(itemList[it].data?.get(0)?.date_created ?: ""),
                            title = itemList[it].data?.get(0)?.title
                    )
                }
        )
    }

    fun mapEntitiesToSimpleModel(itemList: List<NasaEntity>): SimpleMarsRobotsModel {
        return SimpleMarsRobotsModel(
                itemList = List(size = itemList.size) {
                    SimpleMarsRobotsModel.SimpleMarsRobotsItem(
                            imageAddress = itemList[it].links?.get(0)?.href,
                            date = trivialDateFormat(itemList[it].data?.get(0)?.date_created ?: ""),
                            title = itemList[it].data?.get(0)?.title
                    )
                }
        )
    }

    fun mapResponse(response: NasaMarsResponse): List<NasaItem>? {
        val itemList = response.collection?.items
        itemList?.let {
            return List(size = itemList.size) {
                NasaItem(
                        data = mapDataItemList(itemList[it].data),
                        links = mapLinksItemList(itemList[it].links)
                )
            }
        }
        return null
    }

    private fun mapDataItemList(itemList: List<NasaMarsResponse.DataItem>?): List<NasaDataItem>? {
        itemList?.apply {
            return List(size = this.size) {
                mapDataItem(item = this[it])
            }
        }
        return null
    }

    private fun mapLinksItemList(itemList: List<NasaMarsResponse.LinksItem>?): List<NasaLinksItem>? {
        itemList?.apply {
            return List(size = this.size) {
                mapLinksItem(item = this[it])
            }
        }
        return null
    }

    private fun mapDataItem(item: NasaMarsResponse.DataItem): NasaDataItem {
        return NasaDataItem(
                title = item.title,
                nasa_id = item.nasa_id,
                description = item.description,
                date_created = item.date_created,
                photographer = item.photographer
        )
    }

    private fun mapLinksItem(item: NasaMarsResponse.LinksItem): NasaLinksItem {
        return NasaLinksItem(
                rel = item.rel,
                href = item.href,
                render = item.render
        )
    }

    // TODO: Do a nicer date formatter.
    private fun trivialDateFormat(date: String): String {
        val dateSplit = date.split("T")
        if (dateSplit.isNotEmpty()) {
            return dateSplit[0]
        }
        return ""
    }
}