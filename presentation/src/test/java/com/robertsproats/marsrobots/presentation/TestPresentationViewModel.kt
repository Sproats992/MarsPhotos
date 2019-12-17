package com.robertsproats.marsrobots.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.whenever
import com.robertsproats.marsrobots.domain.model.DomainSimpleMarsRobotsModel
import com.robertsproats.marsrobots.domain.usecases.GetMarsRobotsUseCase
import com.robertsproats.marsrobots.presentation.boundary.PresentationModelMapper
import com.robertsproats.marsrobots.presentation.features.home.MarsRobotsViewModel
import com.robertsproats.marsrobots.presentation.model.PresentationSimpleMarsRobotsItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TestPresentationViewModel {

    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Mock
    lateinit var getMarsRobotsUseCase: GetMarsRobotsUseCase

    @Mock
    lateinit var presentationModelMapper: PresentationModelMapper

    private lateinit var flowObject: Flow<DomainSimpleMarsRobotsModel>

    @Test
    fun testStartDataStreamIsLiveDataFakeObserver() = coroutinesTestRule.testDispatcher.runBlockingTest {
        // Given
        val expectedModel = DomainSimpleMarsRobotsModel(
                itemList = listOf(
                        DomainSimpleMarsRobotsModel.DomainSimpleMarsRobotsItem(
                                imageAddress = "imageAddress1",
                                date = "date1",
                                title = "title1"
                        )
                ))

        val expectedPresentationResult = listOf(
                PresentationSimpleMarsRobotsItem(
                        imageAddress = "imageAddress2",
                        date = "date2",
                        title = "title2"
                )
        )

        flowObject = flow {
            emit(expectedModel)
        }

        whenever(getMarsRobotsUseCase.execute()).thenReturn(flowObject)
        whenever(presentationModelMapper.mapSimpleMarsRobotsItems(expectedModel)).thenReturn(expectedPresentationResult)

        val presentationViewModel = MarsRobotsViewModel(
                getMarsRobotsUseCase = getMarsRobotsUseCase,
                presentationModelMapper = presentationModelMapper,
                defaultDispatcher = testDispatcher
        )

        // Then
        presentationViewModel.marsRobotsLiveData.observeOnce {
            Assert.assertEquals(expectedPresentationResult, it)
        }

        presentationViewModel.fetchMarsRobotsData()
    }

    @Test
    fun testStartDataStreamIsLiveDataObserveForever() = coroutinesTestRule.testDispatcher.runBlockingTest {
        // Given
        val expectedModel = DomainSimpleMarsRobotsModel(
                itemList = listOf(
                        DomainSimpleMarsRobotsModel.DomainSimpleMarsRobotsItem(
                                imageAddress = "imageAddress3",
                                date = "date3",
                                title = "title3"
                        )
                ))

        val expectedPresentationResult = listOf(
                PresentationSimpleMarsRobotsItem(
                        imageAddress = "imageAddress4",
                        date = "date4",
                        title = "title4"
                )
        )

        flowObject = flow {
            emit(expectedModel)
        }

        whenever(getMarsRobotsUseCase.execute()).thenReturn(flowObject)
        whenever(presentationModelMapper.mapSimpleMarsRobotsItems(expectedModel)).thenReturn(expectedPresentationResult)
        val presentationViewModel = MarsRobotsViewModel(
                getMarsRobotsUseCase = getMarsRobotsUseCase,
                presentationModelMapper = presentationModelMapper,
                defaultDispatcher = testDispatcher
        )

        // Then
        presentationViewModel.marsRobotsLiveData.observeForever {
            Assert.assertEquals(expectedPresentationResult, it)
        }

        presentationViewModel.fetchMarsRobotsData()
    }

}
