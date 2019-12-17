package com.robertsproats.marsrobots.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.whenever
import com.robertsproats.marsrobots.domain.model.DomainMarsRobotsDetailModel
import com.robertsproats.marsrobots.domain.model.DomainSimpleMarsRobotsModel
import com.robertsproats.marsrobots.domain.usecases.GetMarsRobotsDetailUseCase
import com.robertsproats.marsrobots.domain.usecases.GetMarsRobotsUseCase
import com.robertsproats.marsrobots.presentation.boundary.PresentationModelMapper
import com.robertsproats.marsrobots.presentation.features.detail.MarsRobotsDetailViewModel
import com.robertsproats.marsrobots.presentation.features.home.MarsRobotsViewModel
import com.robertsproats.marsrobots.presentation.model.PresentationMarsRobotsDetailModel
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
class TestPresentationDetailViewModel {

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
    lateinit var getMarsRobotsDetailUseCase: GetMarsRobotsDetailUseCase

    @Mock
    lateinit var presentationModelMapper: PresentationModelMapper

    private lateinit var flowObject: Flow<DomainMarsRobotsDetailModel>

    @Test
    fun testStartDataStreamIsLiveDataFakeObserver() = coroutinesTestRule.testDispatcher.runBlockingTest {
        // Given
        val testIndex = 42
        val expectedModel = DomainMarsRobotsDetailModel(
                imageAddress = "imageDetailAddress1",
                photoCopyright = "photoCopyright1",
                description = "description1",
                title = "title1"
        )


        val expectedPresentationResult = PresentationMarsRobotsDetailModel(
                imageAddress = "imageDetailAddress2",
                photoCopyright = "photoCopyright2",
                description = "description2",
                title = "title2"
        )

        flowObject = flow {
            emit(expectedModel)
        }

        whenever(getMarsRobotsDetailUseCase.execute(testIndex)).thenReturn(flowObject)
        whenever(presentationModelMapper.mapMarsRobotsDetailModel(expectedModel)).thenReturn(expectedPresentationResult)
        val presentationViewModel = MarsRobotsDetailViewModel(
                getMarsRobotsDetailUseCase = getMarsRobotsDetailUseCase,
                presentationModelMapper = presentationModelMapper,
                defaultDispatcher = testDispatcher
        )

        // Then
        presentationViewModel.marsRobotsDetailLiveData.observeOnce {
            Assert.assertEquals(expectedPresentationResult, it)
        }

        presentationViewModel.fetchMarsRobotsDetailData(testIndex)
    }

    @Test
    fun testStartDataStreamIsLiveDataObserveForever() = coroutinesTestRule.testDispatcher.runBlockingTest {
        // Given
        val testIndex = 556
        val expectedModel = DomainMarsRobotsDetailModel(
                imageAddress = "imageDetailAddress3",
                photoCopyright = "photoCopyright3",
                description = "description3",
                title = "title3"
        )


        val expectedPresentationResult = PresentationMarsRobotsDetailModel(
                imageAddress = "imageDetailAddress4",
                photoCopyright = "photoCopyright4",
                description = "description4",
                title = "title4"
        )

        flowObject = flow {
            emit(expectedModel)
        }

        whenever(getMarsRobotsDetailUseCase.execute(testIndex)).thenReturn(flowObject)
        whenever(presentationModelMapper.mapMarsRobotsDetailModel(expectedModel)).thenReturn(expectedPresentationResult)
        val presentationViewModel = MarsRobotsDetailViewModel(
                getMarsRobotsDetailUseCase = getMarsRobotsDetailUseCase,
                presentationModelMapper = presentationModelMapper,
                defaultDispatcher = testDispatcher
        )

        // Then
        presentationViewModel.marsRobotsDetailLiveData.observeForever {
            Assert.assertEquals(expectedPresentationResult, it)
        }

        presentationViewModel.fetchMarsRobotsDetailData(testIndex)
    }

}
