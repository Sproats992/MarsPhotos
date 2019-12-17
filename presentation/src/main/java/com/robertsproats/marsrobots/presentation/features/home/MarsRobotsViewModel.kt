package com.robertsproats.marsrobots.presentation.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robertsproats.marsrobots.domain.usecases.GetMarsRobotsUseCase
import com.robertsproats.marsrobots.presentation.boundary.PresentationModelMapper
import com.robertsproats.marsrobots.presentation.model.PresentationSimpleMarsRobotsItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class MarsRobotsViewModel @Inject constructor(private val presentationModelMapper: PresentationModelMapper,
                                              private val getMarsRobotsUseCase: GetMarsRobotsUseCase,
                                              private val defaultDispatcher: CoroutineDispatcher) : ViewModel() {

    private var liveDataResult = MutableLiveData<List<PresentationSimpleMarsRobotsItem>>()

    val marsRobotsLiveData: LiveData<List<PresentationSimpleMarsRobotsItem>>
        get() = liveDataResult

    fun fetchMarsRobotsData() {
        viewModelScope.launch(defaultDispatcher) {
            getMarsRobotsUseCase.execute(null)
                    .map {
                        presentationModelMapper.mapSimpleMarsRobotsItems(it)
                    }.collect {
                        liveDataResult.postValue(it)
                    }
        }
    }

}