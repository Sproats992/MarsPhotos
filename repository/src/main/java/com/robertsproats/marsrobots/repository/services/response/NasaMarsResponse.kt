package com.robertsproats.repository.services.response

data class NasaMarsResponse(
        val collection: Collection?
) {

    data class Collection(
            val items: List<Item>?
    )

    data class Item(
            val href: String?,
            val data: List<DataItem>?,
            val links: List<LinksItem>?
    )

    data class DataItem(
            val title: String?,
            val nasa_id: String?,
            val description: String?,
            val photographer: String?,
            val date_created: String?
    )

    data class LinksItem(
            val rel: String?,
            val href: String?,
            val render: String?
    )
}
