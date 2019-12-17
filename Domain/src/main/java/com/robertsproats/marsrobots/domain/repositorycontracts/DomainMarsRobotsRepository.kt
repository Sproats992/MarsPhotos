package com.robertsproats.marsrobots.domain.repositorycontracts

import com.robertsproats.marsrobots.domain.model.DomainMarsRobotsDetailModel
import com.robertsproats.marsrobots.domain.model.DomainSimpleMarsRobotsModel
import kotlinx.coroutines.flow.Flow

interface DomainMarsRobotsRepository {

    fun getMarsRobotsData(): Flow<DomainSimpleMarsRobotsModel>

    fun getMarsRobotDetailData(index: Int): Flow<DomainMarsRobotsDetailModel>
}