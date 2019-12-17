package com.robertsproats.marsrobots.domain.usecases

import com.robertsproats.marsrobots.domain.model.DomainSimpleMarsRobotsModel
import com.robertsproats.marsrobots.domain.repositorycontracts.DomainMarsRobotsRepository
import com.robertsproats.marsrobots.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetMarsRobotsUseCase @Inject constructor(
        private val domainMarsRobotsRepository: DomainMarsRobotsRepository
) : UseCase<Void, Flow<DomainSimpleMarsRobotsModel>>() {

    override suspend fun execute(parameters: Void?): Flow<DomainSimpleMarsRobotsModel> {
        return domainMarsRobotsRepository.getMarsRobotsData()
    }

}