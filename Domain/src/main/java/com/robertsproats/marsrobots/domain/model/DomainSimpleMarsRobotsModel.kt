package com.robertsproats.marsrobots.domain.model

data class DomainSimpleMarsRobotsModel(
        val itemList: List<DomainSimpleMarsRobotsItem>
) {
    data class DomainSimpleMarsRobotsItem(
            val imageAddress: String?,
            val title: String?,
            val date: String?
    )
}