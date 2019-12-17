package com.robertsproats.marsrobots.presentation.boundary

import com.robertsproats.marsrobots.domain.model.DomainMarsRobotsDetailModel
import com.robertsproats.marsrobots.domain.model.DomainSimpleMarsRobotsModel
import com.robertsproats.marsrobots.presentation.model.PresentationMarsRobotsDetailModel
import com.robertsproats.marsrobots.presentation.model.PresentationSimpleMarsRobotsItem
import javax.inject.Inject

class PresentationModelMapper @Inject constructor() {

    fun mapSimpleMarsRobotsItems(domainModel: DomainSimpleMarsRobotsModel): List<PresentationSimpleMarsRobotsItem> {
        domainModel.itemList.apply {
            return List(size = domainModel.itemList.size) {
                PresentationSimpleMarsRobotsItem(
                        imageAddress = domainModel.itemList[it].imageAddress,
                        title = domainModel.itemList[it].title,
                        date = domainModel.itemList[it].date
                )
            }

        }
    }

    fun mapMarsRobotsDetailModel(domainModel: DomainMarsRobotsDetailModel): PresentationMarsRobotsDetailModel {
        return PresentationMarsRobotsDetailModel(
                imageAddress = domainModel.imageAddress,
                title = domainModel.title,
                description = domainModel.description,
                photoCopyright = domainModel.photoCopyright
        )
    }
}