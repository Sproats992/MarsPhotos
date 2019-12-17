package com.robertsproats.marsrobots.presentation.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robertsproats.marsrobots.domain.usecases.GetMarsRobotsDetailUseCase
import com.robertsproats.marsrobots.presentation.boundary.PresentationModelMapper
import com.robertsproats.marsrobots.presentation.model.PresentationMarsRobotsDetailModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class MarsRobotsDetailViewModel @Inject constructor(private val presentationModelMapper: PresentationModelMapper,
                                                    private val getMarsRobotsDetailUseCase: GetMarsRobotsDetailUseCase,
                                                    private val defaultDispatcher: CoroutineDispatcher) : ViewModel() {

    private var liveDataResult = MutableLiveData<PresentationMarsRobotsDetailModel>()

    val marsRobotsDetailLiveData: LiveData<PresentationMarsRobotsDetailModel>
        get() = liveDataResult

    fun fetchMarsRobotsDetailData(index: Int) {
        viewModelScope.launch(defaultDispatcher) {
            getMarsRobotsDetailUseCase.execute(index)
                    .map {
                        presentationModelMapper.mapMarsRobotsDetailModel(it)
                    }.collect {
                        liveDataResult.postValue(it)
                    }
        }
    }

}