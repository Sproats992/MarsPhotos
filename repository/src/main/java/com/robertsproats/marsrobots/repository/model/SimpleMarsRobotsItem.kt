package com.robertsproats.marsrobots.repository.model

data class SimpleMarsRobotsModel(
        val itemList: List<SimpleMarsRobotsItem>
) {
    data class SimpleMarsRobotsItem(
            val imageAddress: String?,
            val title: String?,
            val date: String?
    )
}
