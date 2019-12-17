package com.robertsproats.marsrobots.repository

import com.robertsproats.marsrobots.domain.model.DomainMarsRobotsDetailModel
import com.robertsproats.marsrobots.domain.model.DomainSimpleMarsRobotsModel
import com.robertsproats.marsrobots.domain.repositorycontracts.DomainMarsRobotsRepository
import com.robertsproats.marsrobots.repository.boundary.RepositoryModelMapper
import com.robertsproats.marsrobots.repository.database.NasaDatabase
import com.robertsproats.marsrobots.repository.model.NasaItem
import com.robertsproats.marsrobots.repository.model.SimpleMarsRobotsModel
import com.robertsproats.marsrobots.repository.services.NasaClient
import com.robertsproats.marsrobots.repository.utils.Comparator.compareSimpleModels
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NasaMarsRobotsRepository @Inject constructor(
        val nasaClient: NasaClient,
        val repositoryModelMapper: RepositoryModelMapper,
        val nasaDatabase: NasaDatabase
) : DomainMarsRobotsRepository, Repository() {

    var simpleMemoryCacheModel: SimpleMarsRobotsModel? = null

    override fun getMarsRobotsData(): Flow<DomainSimpleMarsRobotsModel> {

        return flow {

            //emit memory cache immediately if it exists.
            simpleMemoryCacheModel?.apply {
                emit(repositoryModelMapper.mapToDomainSimpleModel(this))
            }

            //emit database data if it doesn't match memory cache.
            nasaDatabase.nasaDao().getNasaList()?.apply {
                //if database doesn't match memory cache, emit database data.
                val newSimpleMemoryCacheModel = repositoryModelMapper.mapEntitiesToSimpleModel(this)
                if (!compareSimpleModels(simpleMemoryCacheModel, newSimpleMemoryCacheModel)) {
                    simpleMemoryCacheModel = newSimpleMemoryCacheModel
                    emit(repositoryModelMapper.mapToDomainSimpleModel(newSimpleMemoryCacheModel))
                }
            }

            // then do network load, emit if it's different to memory cache.
            val response = nasaClient.getNasaMarsFeed()
            var nasaItemList: List<NasaItem>? = null
            response.data?.let {
                nasaItemList = repositoryModelMapper.mapResponse(response.data)
                nasaItemList?.let {
                    val newSimpleMemoryCacheModel = repositoryModelMapper.mapToSimpleModel(it)
                    if (!compareSimpleModels(simpleMemoryCacheModel, newSimpleMemoryCacheModel)) {
                        storeInDatabase(it)
                        simpleMemoryCacheModel = newSimpleMemoryCacheModel
                        emit(repositoryModelMapper.mapToDomainSimpleModel(newSimpleMemoryCacheModel))
                    }
                }
            }
        }
    }

    private suspend fun storeInDatabase(data: List<NasaItem>) {
        nasaDatabase.clearAllTables()
        nasaDatabase.nasaDao().insertNasaList(repositoryModelMapper.map(data))
    }

    override fun getMarsRobotDetailData(index: Int): Flow<DomainMarsRobotsDetailModel> {

        return flow {
            nasaDatabase.nasaDao().getNasaList()?.apply {

                if (index < this.size) {

                    var imageAddress: String? = null
                    var title: String? = null
                    var photoCopyright: String? = null
                    var description: String? = null

                    val selectedData = this[index].data?.get(0)
                    val selectedLinks = this[index].links?.get(0)

                    selectedLinks?.let {
                        imageAddress = it.href
                    }

                    selectedData?.let {
                        title = it.title
                        description = it.description
                        photoCopyright = it.photographer
                    }

                    emit(DomainMarsRobotsDetailModel(imageAddress, title, photoCopyright, description))
                } else {

                    emit(DomainMarsRobotsDetailModel(null, null, null, null))

                }
            } ?: run {
                emit(DomainMarsRobotsDetailModel(null, null, null, null))
            }
        }


    }

}