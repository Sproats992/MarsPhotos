package com.robertsproats.marsrobots.domain.usecases

import com.robertsproats.marsrobots.domain.model.DomainMarsRobotsDetailModel
import com.robertsproats.marsrobots.domain.repositorycontracts.DomainMarsRobotsRepository
import com.robertsproats.marsrobots.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetMarsRobotsDetailUseCase @Inject constructor(
        private val domainMarsRobotsRepository: DomainMarsRobotsRepository
) : UseCase<Int, Flow<DomainMarsRobotsDetailModel>>() {

    override suspend fun execute(parameters: Int?): Flow<DomainMarsRobotsDetailModel> {
        parameters?.let {
            return domainMarsRobotsRepository.getMarsRobotDetailData(it)
        } ?: run {
            return flow {
                emit(DomainMarsRobotsDetailModel(
                        null, null, null, null)
                )
            }
        }
    }

}