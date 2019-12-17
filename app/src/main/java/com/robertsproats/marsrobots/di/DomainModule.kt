package com.robertsproats.marsrobots.di

import com.robertsproats.marsrobots.domain.repositorycontracts.DomainMarsRobotsRepository
import com.robertsproats.marsrobots.domain.usecases.GetMarsRobotsUseCase
import com.robertsproats.marsrobots.repository.di.RepositoryModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RepositoryModule::class])
class DomainModule {

    // use cases
    @Provides
    @Singleton
    fun providesGetMarsRobotsUseCase(domainMarsRobotsRepository: DomainMarsRobotsRepository):
            GetMarsRobotsUseCase = GetMarsRobotsUseCase(
            domainMarsRobotsRepository = domainMarsRobotsRepository
    )

}