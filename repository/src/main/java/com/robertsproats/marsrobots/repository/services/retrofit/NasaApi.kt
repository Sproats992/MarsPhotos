package com.robertsproats.repository.services.retrofit

import com.robertsproats.repository.services.response.NasaMarsResponse
import retrofit2.http.GET

interface NasaApi {

    @GET("/search?q=mars&media_type=image")
    suspend fun getNasaMarsData(): NasaMarsResponse

}