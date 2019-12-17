package com.robertsproats.marsrobots.repository.model


data class NasaItem(
        val data: List<NasaDataItem>?,
        val links: List<NasaLinksItem>?
) {

    data class NasaDataItem(
            val title: String?,
            val nasa_id: String?,
            val description: String?,
            val photographer: String?,
            val date_created: String?
    )

    data class NasaLinksItem(
            val rel: String?,
            val href: String?,
            val render: String?
    )
}



