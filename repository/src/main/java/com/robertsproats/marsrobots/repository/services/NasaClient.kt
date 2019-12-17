package com.robertsproats.marsrobots.repository.services

import com.robertsproats.repository.services.response.NasaMarsResponse

interface NasaClient {

    suspend fun getNasaMarsFeed(): Resource<NasaMarsResponse>

}