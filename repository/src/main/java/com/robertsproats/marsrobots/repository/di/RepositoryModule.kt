package com.robertsproats.marsrobots.repository.di

import com.robertsproats.marsrobots.domain.repositorycontracts.DomainMarsRobotsRepository
import com.robertsproats.marsrobots.repository.NasaMarsRobotsRepository
import com.robertsproats.marsrobots.repository.boundary.RepositoryModelMapper
import com.robertsproats.marsrobots.repository.database.NasaDatabase
import com.robertsproats.marsrobots.repository.services.NasaClient
import com.robertsproats.marsrobots.repository.services.ResponseHandler
import com.robertsproats.marsrobots.repository.services.retrofit.OkHttpInterceptor
import com.robertsproats.marsrobots.repository.services.retrofit.RetrofitClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    // repositories
    @Provides
    @Singleton
    fun providesDomainNewsRepository(
            nasaClient: NasaClient,
            repositoryModelMapper: RepositoryModelMapper,
            nasaDatabase: NasaDatabase
    ): DomainMarsRobotsRepository = NasaMarsRobotsRepository(
            nasaClient = nasaClient,
            repositoryModelMapper = repositoryModelMapper,
            nasaDatabase = nasaDatabase
    )

    @Provides
    @Singleton
    fun providesNasaClient(): NasaClient = RetrofitClient(
            okHttpInterceptor = OkHttpInterceptor(),
            responseHandler = ResponseHandler()
    )

    @Provides
    @Singleton
    fun providesMoviesModelMapper(): RepositoryModelMapper = RepositoryModelMapper()

}