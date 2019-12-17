package com.robertsproats.marsrobots.repository.services.retrofit

import com.google.gson.GsonBuilder
import com.robertsproats.marsrobots.repository.services.NasaClient
import com.robertsproats.marsrobots.repository.services.Resource
import com.robertsproats.marsrobots.repository.services.ResponseHandler
import com.robertsproats.marsrobots.repository.utils.RepositoryConstants
import com.robertsproats.repository.services.response.NasaMarsResponse
import com.robertsproats.repository.services.retrofit.NasaApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitClient @Inject constructor(
        private val okHttpInterceptor: OkHttpInterceptor,
        private val responseHandler: ResponseHandler
) : NasaClient {

    private val webservice by lazy {
        Retrofit.Builder()
                .client(okHttpInterceptor.okHttpClient)
                .baseUrl(RepositoryConstants.NASA_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(NasaApi::class.java)
    }

    private var client: NasaApi = webservice

    private suspend fun getHandledNasaData(): Resource<NasaMarsResponse> {
        return try {
            responseHandler.handleSuccess(client.getNasaMarsData())
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    override suspend fun getNasaMarsFeed(): Resource<NasaMarsResponse> = getHandledNasaData()

}